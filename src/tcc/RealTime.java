/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.ChartTheme;

/**
 *
 * @author henike
 */
public class RealTime {

    private XYChart xyChart;
    private static TwoWaySerialComm serialcomm;
    private List<Date> xData;
    private List<Double> yData[] = new List[2];
    private static final String[] SERIES_NAME = {"sensor 1", "sensor 2"};
    private String info;

    public RealTime(String port, String info) {
        this.info = info;

        try {
            serialcomm = new TwoWaySerialComm();
            serialcomm.connect(port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.go();
    }

    private void go() {
        final XChartPanel chartPanel = this.buildPanel();

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
        TimerTask chartUpdaterTask = new TimerTask() {

            @Override
            public void run() {
                updateData();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    chartPanel.updateUI();
                });
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(chartUpdaterTask, 0, 1000);
    }

    private XChartPanel buildPanel() {
        return new XChartPanel(this.getChart());
    }

    private XYChart getChart() {
        xData = getTime();
        yData[0] = getDataSensor1();
        yData[1] = getDataSensor2();

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(ChartTheme.GGPlot2).build();
        xyChart.setTitle(this.info);
        xyChart.setXAxisTitle(this.getAxisXInfo());
        xyChart.setYAxisTitle(this.getAxisYInfo());
        xyChart.addSeries(SERIES_NAME[0], xData, yData[0]);
        xyChart.addSeries(SERIES_NAME[1], xData, yData[1]);

        return xyChart;
    }

    public void updateData() {
        xData.addAll(getTime());
        yData[0].addAll(getDataSensor1());
        yData[1].addAll(getDataSensor2());

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

    private List<Date> getTime() {
        Date hora = Calendar.getInstance().getTime();
        List<Date> data = new CopyOnWriteArrayList<>();
        data.add(hora);

        return data;
    }

    private List<Double> getDataSensor1() {
        List<Double> data = new CopyOnWriteArrayList<>();
        try {
            data.add(Double.parseDouble(serialcomm.getSensor1(this.info)));
        } catch (NumberFormatException | NullPointerException e) {
            data.add(0.0);
        }
        return data;
    }

    private List<Double> getDataSensor2() {
        List<Double> data = new CopyOnWriteArrayList<>();
        try {
            data.add(Double.parseDouble(serialcomm.getSensor2(this.info)));
        } catch (NumberFormatException | NullPointerException e) {
            data.add(0.0);
        }
        return data;
    }

    private String getAxisXInfo() {
        return "hora";
    }

    private String getAxisYInfo() {
        if (this.info.equals("Temperatura")) {
            return "°C";
        }
        return "%";
    }

}
