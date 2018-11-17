package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Estatistica;
import util.ConectaBanco;

/**
 *
 * @author usuario
 */
public abstract class DAO {

    private final ConectaBanco conecta = ConectaBanco.getInstance();
    private final String sensor;
    private final String data_hora_ini, data_hora_fim;

    public DAO(String sensor, String data_hora_ini, String data_hora_fim) {
        this.sensor = sensor;
        this.data_hora_ini = data_hora_ini;
        this.data_hora_fim = data_hora_fim;
    }

    public ConectaBanco getConecta() {
        return conecta;
    }

    public String getSensor() {
        return sensor;
    }

    public String getData_hora_ini() {
        return data_hora_ini;
    }

    public String getData_hora_fim() {
        return data_hora_fim;
    }

    public List<Date> getTimes() {
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

    public abstract List<Double> getValues(String info, Estatistica estatistica, String classe);

    public abstract void setValues(String data_hora, double[] valor, Estatistica estatistica, String classe);

}
