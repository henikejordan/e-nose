package controle;

import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import modelo.Gases;
import modelo.Sensor;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author Henike
 */
public abstract class Chart {

    private Sensor sensor;
    private XYChart xyChart;
    private DAO dao = new DAO();

    public Chart(Sensor sensor) {
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public XYChart getXyChart() {
        return xyChart;
    }

    public void setXyChart(XYChart xyChart) {
        this.xyChart = xyChart;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public String getXAxisInfo() {
        return "hora";
    }

    public String getYAxisInfo() {
        return sensor.getUnidade();
    }

    public void createPanel(final XChartPanel chartPanel) {
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

    public void createChart(List<Date> xData, List<Double>[] yData) {
        String[] aux = getSensor().getInfo();

        setXyChart(new XYChartBuilder().width(500).height(400).theme(Styler.ChartTheme.GGPlot2).build());

        if (getSensor() instanceof Gases) {
            getXyChart().setTitle("Gases");
        } else {
            getXyChart().setTitle(aux[0]);
        }

        getXyChart().setXAxisTitle(getXAxisInfo());
        getXyChart().setYAxisTitle(getYAxisInfo());

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            getXyChart().addSeries(aux[i], xData, yData[i]);
        }
    }

    public abstract void go();

    public abstract XYChart getChart();

    public abstract List<Double> getDataSensors(int indice, int num);

    public abstract List<Date> getTime();

}