package dissertacao;

import java.util.Date;
import java.util.List;
import org.knowm.xchart.XYChart;

/**
 *
 * @author Henike
 */
public interface Chart {

    /**
     * Returns chart.
     *
     * @return
     */
    public XYChart getChart();

    /**
     * Returns values of times.
     *
     * @return
     */
    public List<Date> getTime();

    /**
     * Returns values of sensors.
     *
     * @param i
     * @return
     */
    public List<Double> getDataSensors(int i);

    /**
     * Returns legend X axis.
     *
     * @return
     */
    public String getXAxisInfo();

    /**
     * Returns legend Y axis.
     *
     * @return
     */
    public String getYAxisInfo();

    /**
     * Returns instance object.
     *
     * @return
     */
    public boolean getInstance();

}
