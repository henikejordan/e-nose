package dissertacao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.ChartTheme;

/**
 *
 * @author Henike
 */
public final class RealTimeChart implements Chart {

    private final int N = 8;
    private XYChart xyChart;
    private static TwoWaySerialComm serialcomm;
    private static final Connect CONNECT = new Connect();
    private List<Date> xData;
    private List<Double>[] ySensors = new List[N];
    private List<Double> yTemp, yUmidade, yPressao;
    private static final String[] SENSORS_NAME = {"MQ-3", "MQ-4", "MQ-5", "MQ-6", "MQ-7", "MQ-8", "MQ-9", "MQ-135"};
    private String info, data_hora;
    private double[] valorSensor = new double[N];
    private double valorTemp = 0.0, valorUmidade = 0.0, valorPressao = 0.0;
    private Timer timer = new Timer();
    private boolean instance = true;
    private long seconds;

    /**
     * Constructor.
     *
     * @param port
     * @param info
     * @param seconds
     */
    public RealTimeChart(String port, String info, long seconds) {
        this.info = info;
        this.seconds = seconds;

        try {
            serialcomm = new TwoWaySerialComm();
            serialcomm.connect(port, 9600);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        go();
    }

    /**
     * Start chart construction.
     *
     */
    private void go() {
        final XChartPanel chartPanel = buildPanel();
        TimerTask chartUpdaterTask;

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create and set up the window.
            JFrame frame = new JFrame("XChart");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(chartPanel);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent ev) {
                    timer.cancel();
                    instance = false;
                }
            });

            // Display the window.
            frame.pack();
            frame.setVisible(true);
        });

        // Simulate a data feed
        chartUpdaterTask = new TimerTask() {

            @Override
            public void run() {
                updateData();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    chartPanel.updateUI();
                });
            }
        };

        try {
            timer.scheduleAtFixedRate(chartUpdaterTask, 0, seconds * 1000);
        } catch (IllegalArgumentException ex) {

        }
    }

    /**
     * Build panel chart.
     *
     * @return
     */
    private XChartPanel buildPanel() {
        return new XChartPanel(getChart());
    }

    /**
     * Update the chart in real time.
     *
     */
    private void updateData() {
        xData.addAll(getTime());

        switch (info) {
            case "Temperatura":
                yTemp.addAll(getDataSensors(8));
                break;
            case "Umidade do Ar":
                yUmidade.addAll(getDataSensors(12));
                break;
            case "Pressão Atmosférica":
                yPressao.addAll(getDataSensors(10));
                break;
            default:
                for (int i = 0; i < N; i++) {
                    ySensors[i].addAll(getDataSensors(i));
                }
                break;
        }

        storeData();

        // Limit the total number of points
        while (xData.size() > 50) {
            xData.remove(0);
        }

        switch (info) {
            case "Temperatura":
                while (yTemp.size() > 50) {
                    yTemp.remove(0);
                }
                xyChart.updateXYSeries(info, xData, yTemp, null);
                break;
            case "Umidade do Ar":
                while (yUmidade.size() > 50) {
                    yUmidade.remove(0);
                }
                xyChart.updateXYSeries(info, xData, yUmidade, null);
                break;
            case "Pressão Atmosférica":
                while (yPressao.size() > 50) {
                    yPressao.remove(0);
                }
                xyChart.updateXYSeries(info, xData, yPressao, null);
                break;
            default:
                // Limit the total number of points
                for (int i = 0; i < N; i++) {
                    while (ySensors[i].size() > 50) {
                        ySensors[i].remove(0);
                    }
                }

                for (int i = 0; i < N; i++) {
                    xyChart.updateXYSeries(SENSORS_NAME[i], xData, ySensors[i], null);
                }
                break;
        }
    }

    /**
     * Returns chart.
     *
     * @return
     */
    @Override
    public XYChart getChart() {
        xData = getTime();

        switch (info) {
            case "Temperatura":
                yTemp = getDataSensors(8);
                break;
            case "Umidade do Ar":
                yUmidade = getDataSensors(12);
                break;
            case "Pressão Atmosférica":
                yPressao = getDataSensors(10);
                break;
            default:
                for (int i = 0; i < N; i++) {
                    ySensors[i] = getDataSensors(i);
                }
                break;
        }

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(ChartTheme.GGPlot2).build();
        xyChart.setTitle(info);
        xyChart.setXAxisTitle(getXAxisInfo());
        xyChart.setYAxisTitle(getYAxisInfo());

        switch (info) {
            case "Temperatura":
                xyChart.addSeries(info, xData, yTemp);
                break;
            case "Umidade do Ar":
                xyChart.addSeries(info, xData, yUmidade);
                break;
            case "Pressão Atmosférica":
                xyChart.addSeries(info, xData, yPressao);
                break;
            default:
                for (int i = 0; i < N; i++) {
                    xyChart.addSeries(SENSORS_NAME[i], xData, ySensors[i]);
                }
                break;
        }

        return xyChart;
    }

    /**
     * Returns values of times.
     *
     * @return
     */
    @Override
    public List<Date> getTime() {
        Date hora = Calendar.getInstance().getTime();
        List<Date> data = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        data_hora = dateFormat.format(date);
        data.add(hora);

        return data;
    }

    /**
     * Returns values of sensors.
     *
     * @return
     */
    @Override
    public List<Double> getDataSensors(int i) {
        List<Double> data = new ArrayList<>();
        try {
            switch (info) {
                case "Temperatura":
                    valorTemp = Double.parseDouble(serialcomm.getData(i));
                    break;
                case "Umidade do Ar":
                    valorUmidade = Double.parseDouble(serialcomm.getData(i));
                    break;
                case "Pressão Atmosférica":
                    valorPressao = Double.parseDouble(serialcomm.getData(i));
                    break;
                default:
                    valorSensor[i] = Double.parseDouble(serialcomm.getData(i));
                    break;
            }
        } catch (NumberFormatException | NullPointerException e) {

        }
        switch (info) {
            case "Temperatura":
                data.add(valorTemp);
                break;
            case "Umidade do Ar":
                data.add(valorUmidade);
                break;
            case "Pressão Atmosférica":
                data.add(valorPressao);
                break;
            default:
                data.add(valorSensor[i]);
                break;
        }
        return data;
    }

    /**
     * Returns legend X axis.
     *
     * @return
     */
    @Override
    public String getXAxisInfo() {
        return "hora";
    }

    /**
     * Returns legend Y axis.
     *
     * @return
     */
    @Override
    public String getYAxisInfo() {
        switch (info) {
            case "Temperatura":
                return "°C";
            case "Umidade do Ar":
                return "%";
            case "Pressão Atmosférica":
                return "Atm";
            default:
                return "Analog";
        }
    }

    /**
     * Returns instance object.
     *
     * @return
     */
    @Override
    public boolean getInstance() {
        return instance;
    }

    /**
     * Save the data in the database.
     *
     */
    private void storeData() {
        switch (info) {
            case "Temperatura":
                CONNECT.setValues(info, data_hora, valorTemp);
                break;
            case "Umidade do Ar":
                CONNECT.setValues(info, data_hora, valorUmidade);
                break;
            case "Pressão Atmosférica":
                CONNECT.setValues(info, data_hora, valorPressao);
                break;
            default:
                for (int i = 0; i < N; i++) {
                    CONNECT.setValues(SENSORS_NAME[i], data_hora, valorSensor[i]);
                }
                break;
        }
    }

}
