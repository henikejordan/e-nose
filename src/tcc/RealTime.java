/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;

/**
 *
 * @author henike
 */
public class RealTime implements ExampleChart<XYChart> {

    private XYChart xyChart;
    private static TwoWaySerialComm serialcomm;
    private List<Double> yData, yData2;
    public static final String SERIES_NAME = "sensor 1";
    public static final String SERIES_NAME2 = "sensor 2";
    private String informacao;

    public RealTime(String porta, String informacao) {
        this.informacao = informacao;
        try {
            serialcomm = new TwoWaySerialComm();
            serialcomm.connect(porta);
        } catch (Exception e) {
        }
        this.go();
    }

    private void go() {
        final SwingWrapper<XYChart> swingWrapper = new SwingWrapper<>(getChart());
        swingWrapper.displayChart();

        // Simulate a data feed
        TimerTask chartUpdaterTask = new TimerTask() {

            @Override
            public void run() {
                updateData();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    swingWrapper.repaintChart();
                });
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(chartUpdaterTask, 0, 200);
    }

    @Override
    public XYChart getChart() {
        yData = getDataSensor1();
        yData2 = getDataSensor2();

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(ChartTheme.GGPlot2).title(this.informacao).build();
        xyChart.addSeries(SERIES_NAME, null, yData);
        xyChart.addSeries(SERIES_NAME2, null, yData2);

        return xyChart;
    }

    public void updateData() {
        yData.addAll(getDataSensor1());
        yData2.addAll(getDataSensor2());

        // Limit the total number of points
        while (yData.size() > 50) {
            yData.remove(0);
        }

        // Limit the total number of points
        while (yData2.size() > 50) {
            yData2.remove(0);
        }

        xyChart.updateXYSeries(SERIES_NAME, null, yData, null);
        xyChart.updateXYSeries(SERIES_NAME2, null, yData2, null);
    }

    private List<Double> getDataSensor1() {
        List<Double> data = new CopyOnWriteArrayList<>();
        try {
            if ("Temperatura".equals(this.informacao)) {
                data.add(Double.parseDouble(serialcomm.getTemperaturaSensor1()));
            } else {
                data.add(Double.parseDouble(serialcomm.getLuminosidadeSensor1()));
            }
        } catch (NumberFormatException | NullPointerException e) {
            data.add(0.0);
        }
        return data;
    }

    private List<Double> getDataSensor2() {
        List<Double> data = new CopyOnWriteArrayList<>();
        try {
            if ("Temperatura".equals(this.informacao)) {
                data.add(Double.parseDouble(serialcomm.getTemperaturaSensor2()));
            } else {
                data.add(Double.parseDouble(serialcomm.getLuminosidadeSensor2()));
            }
        } catch (NumberFormatException | NullPointerException e) {
            data.add(0.0);
        }
        return data;
    }

}
