package dissertacao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author Henike
 */
public final class LineChart implements Chart {

    private final int N = 8;
    private XYChart xyChart;
    private static final Connect CONNECT = new Connect();
    private List<Date> xData;
    private final List<Double>[] ySensors = new List[N];
    private List<Double> yTemp, yUmidade, yPressao;
    private static final String[] SENSORS_NAME = {"MQ-3", "MQ-4", "MQ-5", "MQ-6", "MQ-7", "MQ-8", "MQ-9", "MQ-135"};
    private final String info, data_hora_ini, data_hora_fim;
    private boolean instance = true;

    /**
     * Constructor.
     *
     * @param info
     * @param data_hora_ini
     * @param data_hora_fim
     */
    public LineChart(String info, String data_hora_ini, String data_hora_fim) {
        this.info = info;
        this.data_hora_ini = data_hora_ini;
        this.data_hora_fim = data_hora_fim;
        go();
    }

    /**
     * Start chart construction.
     *
     */
    private void go() {
        final XChartPanel chartPanel = buildPanel();

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
                    instance = false;
                }
            });

            // Display the window.
            frame.pack();
            frame.setVisible(true);
        });
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
     * Equals the number of sensor values.
     *
     */
    private void equals() {
        Integer[] num = new Integer[N + 1];
        Integer[] num2 = new Integer[2];

        switch (info) {
            case "Temperatura":
                num2[0] = xData.size();
                num2[1] = yTemp.size();
                break;
            case "Umidade do Ar":
                num2[0] = xData.size();
                num2[1] = yUmidade.size();
                break;
            case "Pressão Atmosférica":
                num2[0] = xData.size();
                num2[1] = yPressao.size();
                break;
            default:
                num[0] = xData.size();
                for (int i = 0; i < N; i++) {
                    num[i + 1] = ySensors[i].size();
                }
                break;
        }

        List nums;

        switch (info) {
            case "Temperatura":
                nums = Arrays.asList(num2);

                while (xData.size() > (int) Collections.min(nums)) {
                    xData.remove(xData.size() - 1);
                }
                break;
            case "Umidade do Ar":
                nums = Arrays.asList(num2);

                while (xData.size() > (int) Collections.min(nums)) {
                    xData.remove(xData.size() - 1);
                }
                break;
            case "Pressão Atmosférica":
                nums = Arrays.asList(num2);

                while (xData.size() > (int) Collections.min(nums)) {
                    xData.remove(xData.size() - 1);
                }
                break;
            default:
                nums = Arrays.asList(num);

                while (xData.size() > (int) Collections.min(nums)) {
                    xData.remove(xData.size() - 1);
                }
                break;
        }

        switch (info) {
            case "Temperatura":
                while (yTemp.size() > (int) Collections.min(nums)) {
                    yTemp.remove(yTemp.size() - 1);
                }
                break;
            case "Umidade do Ar":
                while (yUmidade.size() > (int) Collections.min(nums)) {
                    yUmidade.remove(yUmidade.size() - 1);
                }
                break;
            case "Pressão Atmosférica":
                while (yPressao.size() > (int) Collections.min(nums)) {
                    yPressao.remove(yPressao.size() - 1);
                }
                break;
            default:
                for (int i = 0; i < N; i++) {
                    while (ySensors[i].size() > (int) Collections.min(nums)) {
                        ySensors[i].remove(ySensors[i].size() - 1);
                    }
                }
                break;
        }

        switch (info) {
            case "Temperatura":
                if (num2[0] == 0) {
                    xData.add(Calendar.getInstance().getTime());
                    yTemp.add(0.0);
                }
                break;
            case "Umidade do Ar":
                if (num2[0] == 0) {
                    xData.add(Calendar.getInstance().getTime());
                    yUmidade.add(0.0);
                }
                break;
            case "Pressão Atmosférica":
                if (num2[0] == 0) {
                    xData.add(Calendar.getInstance().getTime());
                    yPressao.add(0.0);
                }
                break;
            default:
                if (num[0] == 0) {
                    xData.add(Calendar.getInstance().getTime());
                    for (int i = 0; i < N; i++) {
                        ySensors[i].add(0.0);
                    }
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

        equals();

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(Styler.ChartTheme.GGPlot2).build();
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
        return CONNECT.getTimes(SENSORS_NAME[0], data_hora_ini, data_hora_fim);
    }

    /**
     * Returns values of sensors.
     *
     * @return
     */
    @Override
    public List<Double> getDataSensors(int i) {
        try {
            switch (info) {
                case "Temperatura":
                    return CONNECT.getValuesSensor(info, data_hora_ini, data_hora_fim);
                case "Umidade do Ar":
                    return CONNECT.getValuesSensor(info, data_hora_ini, data_hora_fim);
                case "Pressão Atmosférica":
                    return CONNECT.getValuesSensor(info, data_hora_ini, data_hora_fim);
                default:
                    return CONNECT.getValuesSensor(SENSORS_NAME[i], data_hora_ini, data_hora_fim);
            }
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
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

}
