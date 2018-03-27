package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author henike
 */
public class ConectaBanco {

    private static ConectaBanco instance = null;
    private Statement stm; //responsável por preparar e realizar pesquisas no BD
    private Connection conn; //responsável por realizar a conexão com o BD
    private final String driver = "org.postgresql.Driver";
    private final String caminho = "jdbc:postgresql://localhost:5432/";
    private final String usuario = "postgres";
    private final String senha = "12345";

    private ConectaBanco() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            conn = DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    public static ConectaBanco getInstance() {
        if (instance == null) {
            instance = new ConectaBanco();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public ResultSet executaSQL(String sql) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return stm.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
        return null;
    }

    public void desconecta() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }
}
