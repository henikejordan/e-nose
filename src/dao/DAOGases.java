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

    private final double min;
    private final double max;

    public DAOGases(String sensor, String data_hora_ini, String data_hora_fim) {
        super(sensor, data_hora_ini, data_hora_fim);
        String[] data_ini = data_hora_ini.split(" "), data_fim = data_hora_fim.split(" ");
        min = getMinimo(data_ini[0], data_fim[0]);
        max = getMaximo(data_ini[0], data_fim[0]);
    }

    @Override
    public List<Double> getValues(String info, Estatistica estatistica) {
        List<Double> data = new ArrayList<>();
        ResultSet resultado = getConecta().executaSQL("select * from gases "
                + "where data_hora >= '" + getData_hora_ini() + "' "
                + "and data_hora <= '" + getData_hora_fim() + "' "
                //+ "and data_hora::text like '____-__-__ __:__:_0' "
                + "order by data_hora");

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
    public synchronized void setValues(String data_hora, double[] valor, Estatistica estatistica) {
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

    private double getMaximo(String data_ini, String data_fim) {
        double maximo = 0;
        ResultSet resultado = getConecta().executaSQL("select max(maximo) from (select "
                + "( "
                + "select max(v) "
                + "from (values (mq2), (mq3), (mq4), (mq5), (mq6), (mq7), (mq8), (mq9), (mq135)) as value(v) "
                + ") as maximo, data_hora "
                + "from gases) as maxgases "
                + "where data_hora >= '" + data_ini + " 00:00:00' "
                + "and data_hora <= '" + data_fim + " 23:59:59' ");

        try {
            while (resultado.next()) {
                maximo = resultado.getDouble("max");
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return maximo;
    }

    private double getMinimo(String data_ini, String data_fim) {
        double minimo = 0;
        ResultSet resultado = getConecta().executaSQL("select min(minimo) from (select "
                + "( "
                + "select min(v) "
                + "from (values (mq2), (mq3), (mq4), (mq5), (mq6), (mq7), (mq8), (mq9), (mq135)) as value(v) "
                + ") as minimo, data_hora "
                + "from gases) as mingases "
                + "where data_hora >= '" + data_ini + " 00:00:00' "
                + "and data_hora <= '" + data_fim + " 23:59:59' ");

        try {
            while (resultado.next()) {
                minimo = resultado.getDouble("min");
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

        return minimo;
    }

}
