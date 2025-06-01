package dao;

import miniSahibinden.DBConnection;
import model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LocationDAO {
    public Location getLocationById(int locationId) throws Exception {
        String sql = "SELECT * FROM Locations WHERE location_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, locationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Location(
                        rs.getInt("location_id"),
                        rs.getString("city")
                );
            }

            return null;
        }
    }
}