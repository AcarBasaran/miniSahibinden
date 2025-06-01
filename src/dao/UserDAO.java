package dao;

import miniSahibinden.DBConnection;
import model.User;

import java.sql.*;

public class UserDAO {
    public User getUserById(int userId) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Users WHERE user_id = ?"
        );
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getLong("phone"),
                    rs.getInt("location_id")
            );
        }

        return null;
    }
}
