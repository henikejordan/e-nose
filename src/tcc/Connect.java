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

/**
 *
 * @author Henike
 */
public class Connect {

    private final String ADAPTER = "org.sqlite.JDBC";
    private final String CONNECTION = "jdbc:sqlite:data.db";
    private Connection c;
    private Statement stmt;

    public void setValues(Values[] values) {
        if (values == null) {
            System.out.println("Values is null");
            return;
        }

        try {
            Class.forName(this.ADAPTER);
            c = DriverManager.getConnection(this.CONNECTION);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO DADOS"
                    + "(sensor, data_hora, temperatura, luminosidade, umidade_ar, umidade_solo)"
                    + " VALUES (" + values[0].getSensor() + "," + values[0].getData_hora() + ","
                    + values[0].getTemperatura() + "," + values[0].getLuminosidade() + ","
                    + values[0].getUmidade_ar() + "," + values[0].getUmidade_solo() + ")";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO DADOS"
                    + "(sensor, data_hora, temperatura, luminosidade, umidade_ar, umidade_solo)"
                    + " VALUES (" + values[1].getSensor() + "," + values[1].getData_hora() + ","
                    + values[1].getTemperatura() + "," + values[1].getLuminosidade() + ","
                    + values[1].getUmidade_ar() + "," + values[1].getUmidade_solo() + ")";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public Object getValues(String info, String data_hora_ini, String data_hora_fim) {
        try {
            Class.forName(this.ADAPTER);
            c = DriverManager.getConnection(this.CONNECTION);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM DADOS "
                    + "WHERE data_hora > " + data_hora_ini + "AND data_hora < " + data_hora_fim)) {
                while (rs.next()) {
                    String sensor = rs.getString("sensor");
                    if (sensor.equals("SENSOR 1")) {
                        System.out.println("SENSOR = " + sensor);
                        String data_hora = rs.getString("data_hora");
                        System.out.println("DATA_HORA = " + data_hora);
                        switch (info) {
                            case "Temperatura":
                                float temperatura = rs.getFloat("temperatura");
                                System.out.println("TEMPERATURA = " + temperatura);
                                //adicionar no array dos dados
                                break;
                            case "Luminosidade":
                                float luminosidade = rs.getFloat("luminosidade");
                                System.out.println("LUMINOSIDADE = " + luminosidade);
                                //adicionar no array dos dados
                                break;
                            case "Umidade do ar":
                                float umidade_ar = rs.getFloat("umidade_ar");
                                System.out.println("UMIDADE_AR = " + umidade_ar);
                                //adicionar no array dos dados
                                break;
                            case "Umidade do solo":
                                float umidade_solo = rs.getFloat("umidade_solo");
                                System.out.println("UMIDADE_SOLO = " + umidade_solo);
                                //adicionar no array dos dados
                                break;
                            default:
                                break;
                        }
                    } else {
                        String data_hora = rs.getString("data_hora");
                        System.out.println("DATA_HORA = " + data_hora);
                        switch (info) {
                            case "Temperatura":
                                float temperatura = rs.getFloat("temperatura");
                                System.out.println("TEMPERATURA = " + temperatura);
                                //adicionar no array dos dados
                                break;
                            case "Luminosidade":
                                float luminosidade = rs.getFloat("luminosidade");
                                System.out.println("LUMINOSIDADE = " + luminosidade);
                                //adicionar no array dos dados
                                break;
                            case "Umidade do ar":
                                float umidade_ar = rs.getFloat("umidade_ar");
                                System.out.println("UMIDADE_AR = " + umidade_ar);
                                //adicionar no array dos dados
                                break;
                            case "Umidade do solo":
                                float umidade_solo = rs.getFloat("umidade_solo");
                                System.out.println("UMIDADE_SOLO = " + umidade_solo);
                                //adicionar no array dos dados
                                break;
                            default:
                                break;
                        }
                    }
                    System.out.println();
                }
            }
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return null;
    }

}
