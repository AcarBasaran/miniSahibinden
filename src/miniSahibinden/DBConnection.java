package miniSahibinden;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/miniSahibinden";
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = "Cemil2018"; // your MySQL password

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }
}

