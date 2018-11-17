package controle;

import util.TwoWaySerialComm;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import dao.DAO;
import modelo.Estatistica;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import modelo.Sensor;

/**
 *
 * @author Henike
 */
public final class GravacaoChart extends Chart {

    private static TwoWaySerialComm serialComm;
    private String dataHora;
    private double[] valor;
    private Timer timer = new Timer();
    private long seconds;
    private List<Date> xData;
    private List<Double>[] yData;

    public GravacaoChart(Sensor sensor, String port, long seconds, String classe, DAO dao, Estatistica estatistica) {
        super(sensor, classe, dao, estatistica);
        this.seconds = seconds;
        valor = new double[sensor.getInfo().length];
        yData = new List[sensor.getInfo().length];

        try {
            serialComm = new TwoWaySerialComm();
            serialComm.connect(port, 9600);
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        go();
    }

    @Override
    public void go() {
        final XChartPanel chartPanel = new XChartPanel(getChart());
        createPanel(chartPanel);

        TimerTask chartUpdaterTask = new TimerTask() {
            @Override
            public void run() {
                updateData();
                javax.swing.SwingUtilities.invokeLater(() -> {
                    chartPanel.updateUI();
                });
                if (!chartPanel.isShowing()) {
                    this.cancel();
                }
            }
        };

        try {
            timer.scheduleAtFixedRate(chartUpdaterTask, 0, seconds * 1000);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    @Override
    public XYChart getChart() {
        xData = getTime();

        int[] indices = getSensor().getIndices();
        for (int i = 0; i < getSensor().getIndices().length; i++) {
            yData[i] = getData(i, indices[i]);
        }

        createChart(xData, yData);

        return getXyChart();
    }

    @Override
    public List<Double> getData(int indice, int num) {
        List<Double> data = new ArrayList<>();
        try {
            valor[indice] = Double.parseDouble(serialComm.getData(num));
        } catch (NumberFormatException | NullPointerException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        data.add(valor[indice]);

        return data;
    }

    @Override
    public List<Date> getTime() {
        Date hora = Calendar.getInstance().getTime();
        List<Date> data = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dataHora = dateFormat.format(date);
        data.add(hora);

        return data;
    }

    private void updateData() {
        xData.addAll(getTime());

        int[] indices = getSensor().getIndices();
        for (int i = 0; i < getSensor().getIndices().length; i++) {
            yData[i].addAll(getData(i, indices[i]));
        }

        //Store data
        getDao().setValues(dataHora, valor, getEstatistica(), getClasse());

        // Limit the total number of points
        while (xData.size() > 50) {
            xData.remove(0);
        }

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            while (yData[i].size() > 50) {
                yData[i].remove(0);
            }
        }

        String[] aux = getSensor().getInfo();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            getXyChart().updateXYSeries(aux[i], xData, yData[i], null);
        }
    }

}
