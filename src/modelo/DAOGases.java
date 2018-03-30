package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henike
 */
public class DAOGases extends DAO {

    public DAOGases(String sensor) {
        super(sensor);
    }

    @Override
    public List<Double> getValuesSensor(String info, String data_hora_ini, String data_hora_fim) {
        List<Double> data = new ArrayList<>();
        ResultSet resultado = getConecta().executaSQL("select * from gases "
                + "where  data_hora >= '" + data_hora_ini + "' and "
                + "data_hora <= '" + data_hora_fim + "' order by data_hora");

        try {
            while (resultado.next()) {
                data.add(resultado.getDouble(info.replaceAll("-", "")));
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

    @Override
    public synchronized void setValues(String data_hora, double[] valor) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(data_hora);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            PreparedStatement pst = getConecta().getConnection().prepareStatement("insert into gases "
                    + "(data_hora, mq2, mq3, mq4, mq5, mq6, mq7, mq8, mq9, mq135) "
                    + "values(?,?,?,?,?,?,?,?,?,?)");
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

}