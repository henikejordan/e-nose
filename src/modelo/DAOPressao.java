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
public class DAOPressao implements DAO {

    private final ConectaBanco conecta = ConectaBanco.getInstance();

    @Override
    public synchronized void setValues(String data_hora, double[] valor) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(data_hora);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            PreparedStatement pst = conecta.getConnection().prepareStatement("insert into pressao "
                    + "(data_hora, pressao) "
                    + "values(?,?)");
            pst.setTimestamp(1, timestamp);
            for (int i = 0; i < valor.length; i++) {
                pst.setDouble(i + 2, valor[i]);
            }
            pst.execute();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    @Override
    public List<Double> getValuesSensor(String info, String data_hora_ini, String data_hora_fim) {
        List<Double> data = new ArrayList<>();
        ResultSet resultado = conecta.executaSQL("select * from pressao "
                + "where  data_hora >= '" + data_hora_ini + "' and "
                + "data_hora <= '" + data_hora_fim + "' order by data_hora");

        try {
            while (resultado.next()) {
                data.add(resultado.getDouble("Pressao"));
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

    @Override
    public List<Date> getTimes(String data_hora_ini, String data_hora_fim) {
        List<Date> data = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ResultSet resultado = conecta.executaSQL("select * from pressao "
                + "where data_hora >= '" + data_hora_ini + "' and "
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
