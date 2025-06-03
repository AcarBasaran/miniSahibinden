package dao;

import miniSahibinden.DBConnection;
import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public List<Car> getAllCars() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Cars");

        List<Car> cars = new ArrayList<>();
        while (rs.next()) {
            cars.add(new Car(rs.getInt("car_id"), rs.getInt("model_id"), rs.getInt("user_id"), rs.getDouble("price"), rs.getInt("year"), rs.getInt("mileage"), rs.getString("date_posted")));
        }
        return cars;
    }

    public Car getCarById(int carId) throws SQLException {
        String sql = "SELECT * FROM Cars WHERE car_id=?";
        Car car = null;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                car = new Car(rs.getInt("car_id"), rs.getInt("model_id"), rs.getInt("user_id"), rs.getDouble("price"), rs.getInt("year"), rs.getInt("mileage"), rs.getString("date_posted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }
}

