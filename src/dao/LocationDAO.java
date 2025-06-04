package dao;

import miniSahibinden.DBConnection;
import model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    public Location getLocationById(int locationId) throws Exception {
        String sql = "SELECT * FROM Locations WHERE location_id = ?";
        Location location = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, locationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Location(
                        rs.getInt("location_id"),
                        rs.getString("city")
                );
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public List<Location> getAllLocations() throws SQLException {
        String sql = "SELECT * FROM Locations";
        List<Location> locations = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                locations.add(new Location(
                        rs.getInt("location_id"),
                        rs.getString("city")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return locations;

    }

    public List<Object[]> getLocationStats() throws SQLException {
        String sql = """
                SELECT Locations.city, COUNT(*) AS numOfCarPerCity
                FROM Locations 
                JOIN Users ON Locations.location_id = Users.location_id
                JOIN Cars ON Users.user_id = Cars.user_id
                GROUP BY Locations.city
                ORDER BY numOfCarPerCity DESC     
                """;
        List<Object[]> locationStats = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String city = rs.getString("city");
                int count = rs.getInt("numOfCarPerCity");
                locationStats.add(new Object[]{city, count});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationStats;

    }
}
