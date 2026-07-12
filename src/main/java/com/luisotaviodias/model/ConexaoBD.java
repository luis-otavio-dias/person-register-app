package com.luisotaviodias.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    private static final String URL = "jdbc:h2:./data/pessoas;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            String sql = "CREATE TABLE IF NOT EXISTS pessoa (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "nome VARCHAR(100), " +
                         "cpf VARCHAR(14), " +
                         "email VARCHAR(100), " +
                         "telefone VARCHAR(20))";
            stmt.execute(sql);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
