package controle;

import modelo.Gases;
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
import modelo.Sensor;

/**
 *
 * @author Henike
 */
public final class LeituraChart extends Chart {

    private final String dataHoraIni, dataHoraFim;
    private List<Date> xData;
    private List<Double>[] yData;

    public LeituraChart(Sensor sensor, String dataHoraIni, String dataHoraFim) {
        super(sensor);
        this.dataHoraIni = dataHoraIni;
        this.dataHoraFim = dataHoraFim;
        yData = new List[sensor.getInfo().length];

        go();
    }

    private void go() {
        final XChartPanel chartPanel = new XChartPanel(getChart());

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

    private void equals() {
        Integer[] num = new Integer[getSensor().getInfo().length + 1];
        num[0] = xData.size();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            num[i + 1] = yData[i].size();
        }

        List nums = Arrays.asList(num);

        while (xData.size() > (int) Collections.min(nums)) {
            xData.remove(xData.size() - 1);
        }

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            while (yData[i].size() > (int) Collections.min(nums)) {
                yData[i].remove(yData[i].size() - 1);
            }
        }

        if (num[0] == 0) {
            xData.add(Calendar.getInstance().getTime());
            for (int i = 0; i < getSensor().getInfo().length; i++) {
                yData[i].add(0.0);
            }
        }
    }

    @Override
    public XYChart getChart() {
        xData = getTime();

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            if (getSensor().getId() < 0) {
                yData[i] = getDataSensors(i);
            } else {
                yData[i] = getDataSensors(0);
            }
        }

        equals();

        // Create Chart
        setXyChart(new XYChartBuilder().width(500).height(400).theme(Styler.ChartTheme.GGPlot2).build());

        String[] aux = getSensor().getInfo();

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

        return getXyChart();
    }

    public List<Double> getDataSensors(int i) {
        String aux[] = getSensor().getInfo();
        return getDao().getValuesSensor(aux[i], dataHoraIni, dataHoraFim);
    }

    @Override
    public List<Date> getTime() {
        String aux[] = getSensor().getInfo();
        return getDao().getTimes(aux[0], dataHoraIni, dataHoraFim);
    }

}
