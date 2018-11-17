package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Estatistica;

/**
 *
 * @author Henike
 */
public class DAOGases extends DAO {

    public DAOGases(String data_hora_ini, String data_hora_fim) {
        super("gases", data_hora_ini, data_hora_fim);
    }

    @Override
    public synchronized List<Double> getValues(String info, Estatistica estatistica, String classe) {
        List<Double> data = new ArrayList<>();
        ResultSet resultado = getConecta().executaSQL("select * from gases "
                + "where data_hora >= '" + getData_hora_ini() + "' "
                + "and data_hora <= '" + getData_hora_fim() + "' "
                + "and classe like '%" + classe + "%' "
                + "order by data_hora");
        estatistica.zeraJanela();

        try {
            while (resultado.next()) {
                data.add(estatistica.calcula(resultado.getDouble(info.replaceAll("-", ""))));
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return data;
    }

    @Override
    public synchronized void setValues(String data_hora, double[] valor, Estatistica estatistica, String classe) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(data_hora);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            PreparedStatement pst = getConecta().getConnection().prepareStatement("insert into gases "
                    + "(data_hora, mq2, mq3, mq4, mq5, mq6, mq7, mq8, mq9, mq135, tgs822, tgs2600, tgs2602, tgs2603, classe) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setTimestamp(1, timestamp);
            for (int i = 0; i < valor.length; i++) {
                pst.setDouble(i + 2, valor[i]);
            }
            pst.setString(15, classe);
            pst.execute();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

}
