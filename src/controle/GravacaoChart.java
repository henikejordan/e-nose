package controle;

import modelo.Gases;
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
import modelo.Sensor;

/**
 *
 * @author Henike
 */
public final class GravacaoChart extends Chart {

    private static TwoWaySerialComm serialComm;
    private String dataHora;
    private double[] valor;
    private Timer timer = new Timer();
    private long seconds;
    private List<Date> xData;
    private List<Double>[] yData;

    public GravacaoChart(Sensor sensor, String port, long seconds) {
        super(sensor);
        this.seconds = seconds;
        valor = new double[sensor.getInfo().length];
        yData = new List[sensor.getInfo().length];

        try {
            serialComm = new TwoWaySerialComm();
            serialComm.connect(port, 9600);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        go();
    }

    private void go() {
        final XChartPanel chartPanel = new XChartPanel(getChart());
        TimerTask chartUpdaterTask;

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create and set up the window.
            JFrame frame = new JFrame("XChart");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(chartPanel);

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
            //
        }
    }

    private void updateData() {
        xData.addAll(getTime());

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            if (getSensor().getId() < 0) {
                yData[i].addAll(getDataSensors(i, i));
            } else {
                yData[i].addAll(getDataSensors(i, getSensor().getId()));
            }
        }

        storeData();

        // Limit the total number of points
        while (xData.size() > 50) {
            xData.remove(0);
        }

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            while (yData[i].size() > 50) {
                yData[i].remove(0);
            }
        }

        String[] aux = getSensor().getInfo();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            getXyChart().updateXYSeries(aux[i], xData, yData[i], null);
        }
    }

    @Override
    public XYChart getChart() {
        xData = getTime();

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            if (getSensor().getId() < 0) {
                yData[i] = getDataSensors(i, i);
            } else {
                yData[i] = getDataSensors(i, getSensor().getId());
            }
        }

        // Create Chart
        setXyChart(new XYChartBuilder().width(500).height(400).theme(ChartTheme.GGPlot2).build());

        if (getSensor() instanceof Gases) {
            getXyChart().setTitle("Gases");
        } else {
            String aux[] = getSensor().getInfo();
            getXyChart().setTitle(aux[0]);
        }

        getXyChart().setXAxisTitle(getXAxisInfo());
        getXyChart().setYAxisTitle(getYAxisInfo());

        String[] aux = getSensor().getInfo();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            getXyChart().addSeries(aux[i], xData, yData[i]);
        }

        return getXyChart();
    }

    public List<Double> getDataSensors(int indice, int num) {
        List<Double> data = new ArrayList<>();
        try {
            valor[indice] = Double.parseDouble(serialComm.getData(num));
        } catch (NumberFormatException | NullPointerException e) {
            //
        }
        data.add(valor[indice]);

        return data;
    }

    @Override
    public List<Date> getTime() {
        Date hora = Calendar.getInstance().getTime();
        List<Date> data = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dataHora = dateFormat.format(date);
        data.add(hora);

        return data;
    }

    private void storeData() {
        String aux[] = getSensor().getInfo();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            getDao().setValues(aux[i], dataHora, valor[i]);
        }
    }

}
