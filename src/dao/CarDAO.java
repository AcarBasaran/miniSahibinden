package dao;

import miniSahibinden.DBConnection;
import model.Car;
import model.Model;

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

    public void addCar(Car car) {
        String sql = """
                INSERT INTO Cars
                (model_id, user_id, price, year, mileage, date_posted)
                VALUES (?,?,?,?,?,?)
                """;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, car.getModelId());
            stmt.setInt(2, car.getUserId());
            stmt.setDouble(3, car.getPrice());
            stmt.setInt(4, car.getYear());
            stmt.setInt(5, car.getMileage());
            stmt.setString(6, car.getDatePosted());

            System.out.println("Car added" + car.toString());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Car> getCarsByUserId(int userId) throws Exception {
        String sql = "SELECT * FROM Cars WHERE user_id = ?";

        List<Car> carsByUser = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                carsByUser.add(new Car(rs.getInt("car_id"), rs.getInt("model_id"), rs.getInt("user_id"), rs.getDouble("price"), rs.getInt("year"), rs.getInt("mileage"), rs.getString("date_posted")));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carsByUser;
    }}

