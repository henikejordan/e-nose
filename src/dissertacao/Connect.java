package dissertacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class Connect {

    private final ConectaBanco conecta = ConectaBanco.getInstance();

    /**
     * Save in the database the sensor values.
     *
     * @param info
     * @param data_hora
     * @param value
     */
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
        } catch (ParseException | SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    /**
     * Returns values of the specified sensor.
     *
     * @param info
     * @param data_hora_ini
     * @param data_hora_fim
     * @return
     */
    public List<Double> getValuesSensor(String info, String data_hora_ini, String data_hora_fim) {
        List<Double> data = new CopyOnWriteArrayList<>();
        ResultSet resultado = conecta.executaSQL("select * from dados "
                + "where informacao = '" + info + "' and "
                + "data_hora > '" + data_hora_ini + "' and "
                + "data_hora < '" + data_hora_fim + "' order by data_hora");
        try {
            while (resultado.next()) {
                data.add(resultado.getDouble("valor"));
            }
            resultado.first();
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

    /**
     * Returns time values of the specified sensor.
     *
     * @param info
     * @param data_hora_ini
     * @param data_hora_fim
     * @return
     */
    public List<Date> getTimes(String info, String data_hora_ini, String data_hora_fim) {
        List<Date> data = new CopyOnWriteArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ResultSet resultado = conecta.executaSQL("select * from dados "
                + "where informacao = '" + info + "' and "
                + "data_hora > '" + data_hora_ini + "' and "
                + "data_hora < '" + data_hora_fim + "' order by data_hora");
        try {
            while (resultado.next()) {
                data.add(new Date(format.parse(resultado.getString("data_hora")).getTime()));
            }
        } catch (ParseException | SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

}
