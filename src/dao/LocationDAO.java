package dao;

import miniSahibinden.DBConnection;
import model.Location;

import java.sql.*;

public class LocationDAO {
    public Location getLocationById(int locationId) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Locations WHERE location_id = ?"
        );
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
