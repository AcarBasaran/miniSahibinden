package dao;

import miniSahibinden.DBConnection;
import model.FuelType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FuelTypeDAO {
    public FuelType getFuelTypeById(int fuelTypeId) throws Exception {
        String sql = "select * from FuelTypes where fuel_type_id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, fuelTypeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new FuelType(rs.getInt("fuel_type_id"), rs.getString("fuel_name"));
            }

            return null;
        }
    }
}