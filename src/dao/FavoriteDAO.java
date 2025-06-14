package dao;

import miniSahibinden.DBConnection;
import model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDAO {
    public List<Car> getFavoriteCarsByUserId(int userId){
        String sql = """
                    SELECT c.*
                    FROM Favorites f
                    JOIN Cars c ON f.car_id = c.car_id
                    WHERE f.user_id = ?
                """;
        List<Car> favoriteCars = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                favoriteCars.add(new Car(rs.getInt("car_id"), rs.getInt("model_id"), rs.getInt("user_id"), rs.getDouble("price"), rs.getInt("year"), rs.getInt("mileage"), rs.getString("date_posted")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteCars;
    }

    public void addFavorite(int user_id, int car_id) {
        String sql = """
                INSERT INTO Favorites VALUES (?,?)
                """;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user_id);
            stmt.setInt(2, car_id);

            stmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteFavorite(int user_id, int car_id) {
        String sql = """
                DELETE FROM Favorites WHERE user_id = ? AND car_id = ?
                """;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user_id);
            stmt.setInt(2, car_id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkFavorite(int user_id, int car_id) {
        String sql = "SELECT 42 FROM Favorites WHERE user_id = ? AND car_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user_id);
            stmt.setInt(2, car_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


}
