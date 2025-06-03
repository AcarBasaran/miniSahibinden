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
    public List<Car> getFavoriteCarsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Favorites WHERE user_id=?";
        List<Car> favoriteCars = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                favoriteCars.add(new Car(
                        rs.getInt("car_id"),
                        rs.getInt("model_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("price"),
                        rs.getInt("year"),
                        rs.getInt("mileage"),
                        rs.getString("date_posted")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteCars;
    }
}
