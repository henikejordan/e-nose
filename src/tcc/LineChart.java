/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.util.Arrays;
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
 * @author henike
 */
public class LineChart {

    private XYChart xyChart;
    private static TwoWaySerialComm serialcomm;
    private static final Connect CONNECT = new Connect();
    private List<Date> xData;
    private List<Double> yData[] = new List[2];
    private static final String[] SERIES_NAME = {"sensor 1", "sensor 2"};
    private final String info, data_hora_ini, data_hora_fim;

    public LineChart(String info, String data_hora_ini, String data_hora_fim) {
        this.info = info;
        this.data_hora_ini = data_hora_ini;
        this.data_hora_fim = data_hora_fim;
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
    }

    private XChartPanel buildPanel() {
        return new XChartPanel(this.getChart());
    }

    private XYChart getChart() {
        xData = getTime();
        yData[0] = getDataSensor1();
        yData[1] = getDataSensor2();
        this.equals();

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(Styler.ChartTheme.GGPlot2).build();
        xyChart.setTitle(this.info);
        xyChart.setXAxisTitle(this.getAxisXInfo());
        xyChart.setYAxisTitle(this.getAxisYInfo());
        xyChart.addSeries(SERIES_NAME[0], xData, yData[0]);
        xyChart.addSeries(SERIES_NAME[1], xData, yData[1]);

        return xyChart;
    }

    private List<Date> getTime() {
        return CONNECT.getTimes(this.info, data_hora_ini, data_hora_fim);
    }

    private List<Double> getDataSensor1() {
        return CONNECT.getValuesSensor("SENSOR 1", this.info, data_hora_ini, data_hora_fim);
    }

    private List<Double> getDataSensor2() {
        return CONNECT.getValuesSensor("SENSOR 2", this.info, data_hora_ini, data_hora_fim);
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

    private void equals() {
        Integer[] num = new Integer[3];
        num[0] = xData.size();
        num[1] = yData[0].size();
        num[2] = yData[1].size();

        List nums = Arrays.asList(num);

        while (xData.size() > (int) Collections.min(nums)) {
            xData.remove(xData.size() - 1);
        }
        while (yData[0].size() > (int) Collections.min(nums)) {
            yData[0].remove(yData[0].size() - 1);
        }
        while (yData[1].size() > (int) Collections.min(nums)) {
            yData[1].remove(yData[1].size() - 1);
        }

    }

}
