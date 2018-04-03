package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.MediaMovel;
import util.ConectaBanco;

/**
 *
 * @author usuario
 */
public abstract class DAO {

    private final ConectaBanco conecta = ConectaBanco.getInstance();
    private final String sensor;

    public DAO(String sensor) {
        this.sensor = sensor;
    }

    public ConectaBanco getConecta() {
        return conecta;
    }

    public List<Date> getTimes(String data_hora_ini, String data_hora_fim) {
        List<Date> data = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ResultSet resultado = getConecta().executaSQL("select * from " + sensor + " "
                + "where data_hora >= '" + data_hora_ini + "' "
                + "and data_hora <= '" + data_hora_fim + "' "
                //+ "and data_hora::text like '____-__-__ __:__:_0' "
                + "order by data_hora");

        try {
            while (resultado.next()) {
                data.add(new Date(format.parse(resultado.getString("data_hora")).getTime()));
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

    public abstract List<Double> getValues(String info, String data_hora_ini, String data_hora_fim, MediaMovel mediaMovel);

    public abstract void setValues(String data_hora, double[] valor, MediaMovel mediaMovel);

}
