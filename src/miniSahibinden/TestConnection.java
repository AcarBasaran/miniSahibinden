package miniSahibinden;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String query = "SELECT * FROM Cars LIMIT 5";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int carId = rs.getInt("car_id");
                double price = rs.getDouble("price");
                int year = rs.getInt("year");
                System.out.println("Car ID: " + carId + ", Price: " + price + ", Year: " + year);
            }

        } catch (SQLException e) {
            System.out.println("❌ Query failed:");
            e.printStackTrace();
        }
    }
}
