/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

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
     * Returns values of sensor 1.
     *
     * @return
     */
    public List<Double> getDataSensor1();

    /**
     * Returns values of sensor 2.
     *
     * @return
     */
    public List<Double> getDataSensor2();

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
