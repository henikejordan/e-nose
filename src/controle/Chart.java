package controle;

import java.util.Date;
import java.util.List;
import modelo.Sensor;
import org.knowm.xchart.XYChart;

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

    public abstract XYChart getChart();

    public abstract List<Date> getTime();

}
