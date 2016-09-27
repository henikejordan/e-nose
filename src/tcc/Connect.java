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

    public Connect() {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dados.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM DADOS;")) {
                while (rs.next()) {
                    int id_dados = rs.getInt("id_dados");
                    String data_hora = rs.getString("data_hora");
                    float temperatura = rs.getFloat("temperatura");
                    float luminosidade = rs.getFloat("luminosidade");
                    float umidade_ar = rs.getFloat("umidade_ar");
                    float umidade_solo = rs.getFloat("umidade_solo");
                    System.out.println("ID_DADOS = " + id_dados);
                    System.out.println("DATA_HORA = " + data_hora);
                    System.out.println("TEMPERATURA = " + temperatura);
                    System.out.println("LUMINOSIDADE = " + luminosidade);
                    System.out.println("UMIDADE_AR = " + umidade_ar);
                    System.out.println("UMIDADE_SOLO = " + umidade_solo);
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
    }

}
