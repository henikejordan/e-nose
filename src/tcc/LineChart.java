/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

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

    private XYChart xyChart;
    private static final Connect CONNECT = new Connect();
    private List<Date> xData;
    private final List<Double> yData[] = new List[2];
    private static final String[] SERIES_NAME = {"sensor 1", "sensor 2"};
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
        this.go();
    }

    /**
     * Start chart construction.
     *
     */
    private void go() {
        final XChartPanel chartPanel = this.buildPanel();

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
        return new XChartPanel(this.getChart());
    }

    /**
     * Equals the number of sensor values.
     *
     */
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

        if (num[0] == 0) {
            xData.add(Calendar.getInstance().getTime());
            yData[0].add(0.0);
            yData[1].add(0.0);
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
        yData[0] = getDataSensor1();
        yData[1] = getDataSensor2();
        this.equals();

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(Styler.ChartTheme.GGPlot2).build();
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
        return CONNECT.getTimes(this.info, data_hora_ini, data_hora_fim);
    }

    /**
     * Returns values of sensor 1.
     *
     * @return
     */
    @Override
    public List<Double> getDataSensor1() {
        return CONNECT.getValuesSensor("SENSOR 1", this.info, data_hora_ini, data_hora_fim);
    }

    /**
     * Returns values of sensor 2.
     *
     * @return
     */
    @Override
    public List<Double> getDataSensor2() {
        return CONNECT.getValuesSensor("SENSOR 2", this.info, data_hora_ini, data_hora_fim);
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

}
