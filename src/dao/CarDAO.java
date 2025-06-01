package dao;

import miniSahibinden.DBConnection;
import model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public List<Car> getAllCars() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Cars");

        List<Car> cars = new ArrayList<>();
        while (rs.next()) {
            cars.add(new Car(
                    rs.getInt("car_id"),
                    rs.getInt("model_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("price"),
                    rs.getInt("year"),
                    rs.getInt("mileage"),
                    rs.getString("date_posted")
            ));
        }
        return cars;
    }
}

