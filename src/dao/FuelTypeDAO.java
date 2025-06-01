package dao;

import miniSahibinden.DBConnection;
import model.FuelType;

import java.sql.*;

public class FuelTypeDAO {
    public FuelType getFuelTypeById(int fuelTypeId) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM FuelTypes WHERE fuel_type_id = ?"
        );
        stmt.setInt(1, fuelTypeId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new FuelType(
                    rs.getInt("fuel_type_id"),
                    rs.getString("fuel_name")
            );
        }

        return null;
    }
}
