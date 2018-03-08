package controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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

    public GravacaoChart(Sensor sensor, String port, long seconds) {
        super(sensor);
        this.seconds = seconds;
        valor = new double[sensor.getInfo().length];
        yData = new List[sensor.getInfo().length];

        try {
            serialComm = new TwoWaySerialComm();
            serialComm.connect(port, 9600);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        go();
    }

    @Override
    public void go() {
        final XChartPanel chartPanel = new XChartPanel(getChart());
        createPanel(chartPanel);

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

        try {
            timer.scheduleAtFixedRate(chartUpdaterTask, 0, seconds * 1000);
        } catch (IllegalArgumentException ex) {
            //
        }
    }

    private void updateData() {
        xData.addAll(getTime());

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            if (getSensor().getId() < 0) {
                yData[i].addAll(getDataSensors(i, i));
            } else {
                yData[i].addAll(getDataSensors(i, getSensor().getId()));
            }
        }

        storeData();

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

    @Override
    public XYChart getChart() {
        xData = getTime();

        for (int i = 0; i < getSensor().getInfo().length; i++) {
            if (getSensor().getId() < 0) {
                yData[i] = getDataSensors(i, i);
            } else {
                yData[i] = getDataSensors(i, getSensor().getId());
            }
        }

        createChart(xData, yData);

        return getXyChart();
    }

    @Override
    public List<Double> getDataSensors(int indice, int num) {
        List<Double> data = new ArrayList<>();
        try {
            valor[indice] = Double.parseDouble(serialComm.getData(num));
        } catch (NumberFormatException | NullPointerException e) {
            //
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

    private void storeData() {
        String aux[] = getSensor().getInfo();
        for (int i = 0; i < getSensor().getInfo().length; i++) {
            getDao().setValues(aux[i], dataHora, valor[i]);
        }
    }

}
