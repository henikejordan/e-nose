/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

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

    private XYChart xyChart;
    private static TwoWaySerialComm serialcomm;
    private static final Connect CONNECT = new Connect();
    private List<Date> xData;
    private List<Double> yData[] = new List[2];
    private static final String[] SERIES_NAME = {"sensor 1", "sensor 2"};
    private String info, data_hora;
    private double valorSensor1, valorSensor2;
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

        this.go();
    }

    /**
     * Start chart construction.
     *
     */
    private void go() {
        final XChartPanel chartPanel = this.buildPanel();
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
        return new XChartPanel(this.getChart());
    }

    /**
     * Update the chart in real time.
     *
     */
    private void updateData() {
        xData.addAll(getTime());
        yData[0].addAll(getDataSensor1());
        yData[1].addAll(getDataSensor2());
        this.storeData();

        // Limit the total number of points
        while (xData.size() > 50) {
            xData.remove(0);
        }

        // Limit the total number of points
        while (yData[0].size() > 50) {
            yData[0].remove(0);
        }

        // Limit the total number of points
        while (yData[1].size() > 50) {
            yData[1].remove(0);
        }

        xyChart.updateXYSeries(SERIES_NAME[0], xData, yData[0], null);
        xyChart.updateXYSeries(SERIES_NAME[1], xData, yData[1], null);
    }

    /**
     * Returns chart.
     *
     * @return
     */
    @Override
    public XYChart getChart() {
        xData = getTime();
        yData[0] = getDataSensor1();
        yData[1] = getDataSensor2();

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(ChartTheme.GGPlot2).build();
        xyChart.setTitle(this.info);
        xyChart.setXAxisTitle(this.getXAxisInfo());
        xyChart.setYAxisTitle(this.getYAxisInfo());
        xyChart.addSeries(SERIES_NAME[0], xData, yData[0]);
        xyChart.addSeries(SERIES_NAME[1], xData, yData[1]);

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
     * Returns values of sensor 1.
     *
     * @return
     */
    @Override
    public List<Double> getDataSensor1() {
        List<Double> data = new ArrayList<>();
        valorSensor1 = 0.0;
        try {
            valorSensor1 = Double.parseDouble(serialcomm.getSensor1(this.info));
        } catch (NumberFormatException | NullPointerException e) {

        }
        data.add(valorSensor1);

        return data;
    }

    /**
     * Returns values of sensor 2.
     *
     * @return
     */
    @Override
    public List<Double> getDataSensor2() {
        List<Double> data = new ArrayList<>();
        valorSensor2 = 0.0;
        try {
            valorSensor2 = Double.parseDouble(serialcomm.getSensor2(this.info));
        } catch (NumberFormatException | NullPointerException e) {

        }
        data.add(valorSensor2);

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
        if (this.info.equals("Temperatura")) {
            return "°C";
        }
        return "%";
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
        if (valorSensor1 != 0.0 && valorSensor2 != 0.0) {
            CONNECT.setValues("SENSOR 1", data_hora, info, valorSensor1);
            CONNECT.setValues("SENSOR 2", data_hora, info, valorSensor2);
        }
    }

}
