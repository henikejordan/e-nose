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

    public XYChart getChart();

    public List<Date> getTime();

    public List<Double> getDataSensor1();

    public List<Double> getDataSensor2();

    public String getAxisXInfo();

    public String getAxisYInfo();

    public boolean getInstance();

}
