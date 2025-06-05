package dao;

import miniSahibinden.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User getUserById(int userId) throws Exception {
        String sql = "select * from Users where user_id=?";
        User user = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getLong("phone"), rs.getInt("location_id"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() throws Exception {
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<User>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getLong("phone"), rs.getInt("location_id")));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByEmailAndPassword(String email, String password) throws Exception {
        String sql = "select * from Users where email=? and password=?";
        User user = null;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getLong("phone"), rs.getInt("location_id"));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void addUser(User user) throws Exception {
        String sql = "INSERT INTO Users VALUES(?,?,?,?,?,?)";
    }
}