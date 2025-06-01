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
}
