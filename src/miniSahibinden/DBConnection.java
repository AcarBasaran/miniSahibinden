package miniSahibinden;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/miniSahibinden";
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = "Cemil2018"; // your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

