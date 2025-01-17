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
public class DAOUmidade extends DAO {

    public DAOUmidade(String data_hora_ini, String data_hora_fim) {
        super("umidade", data_hora_ini, data_hora_fim);
    }

    @Override
    public synchronized List<Double> getValues(String info, Estatistica estatistica, String classe) {
        List<Double> data = new ArrayList<>();
        ResultSet resultado = getConecta().executaSQL("select * from umidade "
                + "where data_hora >= '" + getData_hora_ini() + "' "
                + "and data_hora <= '" + getData_hora_fim() + "' "
                + "and classe like '%" + classe + "%' "
                + "order by data_hora");

        try {
            while (resultado.next()) {
                data.add(estatistica.calcula(resultado.getDouble("Umidade")));
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

            PreparedStatement pst = getConecta().getConnection().prepareStatement("insert into umidade "
                    + "(data_hora, umidade, classe) "
                    + "values(?,?,?)");
            pst.setTimestamp(1, timestamp);
            for (int i = 0; i < valor.length; i++) {
                pst.setDouble(i + 2, valor[i]);
            }
            pst.setString(3, classe);
            pst.execute();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

}
