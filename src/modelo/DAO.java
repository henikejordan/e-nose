package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class DAO {

    private final ConectaBanco conecta = ConectaBanco.getInstance();

    public synchronized void setValues(String info, String data_hora, double value) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(data_hora);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            PreparedStatement pst = conecta.getConnection().prepareStatement("insert into dados"
                    + "(informacao, data_hora, valor) values(?,?,?)");
            pst.setString(1, info);
            pst.setTimestamp(2, timestamp);
            pst.setDouble(3, value);
            pst.execute();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    public List<Double> getValuesSensor(String info, String data_hora_ini, String data_hora_fim) {
        List<Double> data = new ArrayList<>();
        ResultSet resultado = conecta.executaSQL("select * from dados "
                + "where informacao = '" + info + "' and "
                + "data_hora >= '" + data_hora_ini + "' and "
                + "data_hora <= '" + data_hora_fim + "' order by data_hora");

        try {
            while (resultado.next()) {
                data.add(resultado.getDouble("valor"));
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

    public List<Date> getTimes(String info, String data_hora_ini, String data_hora_fim) {
        List<Date> data = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ResultSet resultado = conecta.executaSQL("select * from dados "
                + "where informacao = '" + info + "' and "
                + "data_hora >= '" + data_hora_ini + "' and "
                + "data_hora <= '" + data_hora_fim + "' order by data_hora");

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

}
