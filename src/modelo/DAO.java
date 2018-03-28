package modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface DAO {

    void setValues(String data_hora, double[] valor);

    List<Double> getValuesSensor(String info, String data_hora_ini, String data_hora_fim);

    List<Date> getTimes(String data_hora_ini, String data_hora_fim);

}
