package controle;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import dao.DAO;
import modelo.Estatistica;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import modelo.Sensor;

/**
 *
 * @author Henike
 */
public final class LeituraChart extends Chart {

    private List<Date> xData;
    private final List<Double>[] yData;

    public LeituraChart(Sensor sensor, DAO dao, Estatistica estatistica) {
        super(sensor, dao, estatistica);
        yData = new List[sensor.getInfo().length];

        go();
    }

    @Override
    public void go() {
        final XChartPanel chartPanel = new XChartPanel(getChart());
        createPanel(chartPanel);
    }

    @Override
    public XYChart getChart() {
        xData = getTime();

        for (int i = 0; i < getSensor().getIndices().length; i++) {
            yData[i] = getData(i, i);
        }

        if (xData.isEmpty()) {
            xData.add(Calendar.getInstance().getTime());
            for (int i = 0; i < getSensor().getInfo().length; i++) {
                yData[i].add(0.0);
            }
        }
        createChart(xData, yData);

        return getXyChart();
    }

    @Override
    public List<Double> getData(int indice, int num) {
        String aux[] = getSensor().getInfo();
        return getDao().getValues(aux[indice], getEstatistica());
    }

    @Override
    public List<Date> getTime() {
        return getDao().getTimes();
    }

}
