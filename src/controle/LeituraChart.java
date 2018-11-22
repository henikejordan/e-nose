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

    public LeituraChart(Sensor sensor, String classe, DAO dao, Estatistica estatistica) {
        super(sensor, classe, dao, estatistica);
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

        equals();

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
        return getDao().getValues(aux[indice], getEstatistica(), getClasse());
    }

    @Override
    public List<Date> getTime() {
        return getDao().getTimes(getClasse());
    }

    private void equals() {
        int menor = xData.size();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            if (yData[i].size() < menor) {
                menor = yData[i].size();
            }
        }

        while (xData.size() > menor) {
            xData.remove(xData.size() - 1);
        }

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            while (yData[i].size() > menor) {
                yData[i].remove(yData[i].size() - 1);
            }
        }
    }

}
