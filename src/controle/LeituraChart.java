package controle;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import modelo.Sensor;

/**
 *
 * @author Henike
 */
public final class LeituraChart extends Chart {

    private final String dataHoraIni, dataHoraFim;
    private List<Date> xData;
    private final List<Double>[] yData;

    public LeituraChart(Sensor sensor, String dataHoraIni, String dataHoraFim) {
        super(sensor);
        this.dataHoraIni = dataHoraIni;
        this.dataHoraFim = dataHoraFim;
        yData = new List[sensor.getInfo().length];

        go();
    }

    @Override
    public void go() {
        final XChartPanel chartPanel = new XChartPanel(getChart());
        createPanel(chartPanel);
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

        for (int i = 0; i < getSensor().getIndices().length; i++) {
            yData[i] = getDataSensors(i, i);
        }

        equals();
        createChart(xData, yData);

        return getXyChart();
    }

    @Override
    public List<Double> getDataSensors(int indice, int num) {
        String aux[] = getSensor().getInfo();
        return getDao().getValuesSensor(aux[indice], dataHoraIni, dataHoraFim);
    }

    @Override
    public List<Date> getTime() {
        String aux[] = getSensor().getInfo();
        return getDao().getTimes(aux[0], dataHoraIni, dataHoraFim);
    }

}
