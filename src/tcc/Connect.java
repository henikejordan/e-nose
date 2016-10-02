/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Henike
 */
public class Connect {

    private final String ADAPTER = "org.sqlite.JDBC";
    private final String CONNECTION = "jdbc:sqlite:data.db";
    private Connection c;
    private Statement stmt;

    public synchronized void setValues(String sensor, String data_hora, String info, double value) {
        try {
            Class.forName(this.ADAPTER);
            c = DriverManager.getConnection(this.CONNECTION);
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO DADOS"
                    + "(sensor, data_hora, informacao, valor)"
                    + " VALUES ('" + sensor + "','" + data_hora + "','" + info + "'," + value + ")";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Records created successfully");
    }

    public List<Double> getValuesSensor(String sensor, String info, String data_hora_ini, String data_hora_fim) {
        List<Double> data = new CopyOnWriteArrayList<>();
        try {
            Class.forName(this.ADAPTER);
            c = DriverManager.getConnection(this.CONNECTION);
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM DADOS "
                    + "WHERE sensor = '" + sensor + "' AND "
                    + "informacao = '" + info + "' AND "
                    + "data_hora > '" + data_hora_ini + "' AND "
                    + "data_hora < '" + data_hora_fim + "' ORDER BY data_hora")) {
                while (rs.next()) {
                    data.add(rs.getDouble("valor"));
                }
            }
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Operation done successfully");

        return data;
    }

    public List<Date> getTimes(String info, String data_hora_ini, String data_hora_fim) {
        List<Date> data = new CopyOnWriteArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Class.forName(this.ADAPTER);
            c = DriverManager.getConnection(this.CONNECTION);
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM DADOS "
                    + "WHERE sensor = 'SENSOR 1' AND "
                    + "informacao = '" + info + "' AND "
                    + "data_hora > '" + data_hora_ini + "' AND "
                    + "data_hora < '" + data_hora_fim + "' ORDER BY data_hora")) {
                while (rs.next()) {
                    data.add(new Date(format.parse(rs.getString("data_hora")).getTime()));
                }
            }
            stmt.close();
            c.close();
        } catch (ParseException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Operation done successfully");

        return data;
    }

}
