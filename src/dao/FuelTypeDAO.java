package dao;

import miniSahibinden.DBConnection;
import model.FuelType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuelTypeDAO {
    public FuelType getFuelTypeById(int fuelTypeId) throws Exception {
        String sql = "select * from FuelTypes where fuel_type_id=?";
        FuelType fuelType = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fuelTypeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fuelType = new FuelType(rs.getInt("fuel_type_id"), rs.getString("fuel_name"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return fuelType;
    }

    public List<FuelType> getAllFuelTypes() throws Exception {
        String sql = """
                SELECT * FROM FuelTypes
                """;
        List<FuelType> fuelTypes = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fuelTypes.add(new FuelType(rs.getInt("fuel_type_id"), rs.getString("fuel_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fuelTypes;
    }

    public List<Object[]> getFuelTypesUsageStats()  {
        String sql = """
                SELECT FuelTypes.fuel_name, COUNT(*) AS usage_count
                FROM Cars 
                JOIN Models ON Cars.model_id = Models.model_id 
                JOIN FuelTypes ON Models.fuel_type_id = FuelTypes.fuel_type_id
                GROUP BY FuelTypes.fuel_name
                ORDER BY usage_count DESC
                """;
        List<Object[]> mostUsedFuelTypes = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String fuelName = rs.getString("fuel_name");
                int usageCount = rs.getInt("usage_count");
                mostUsedFuelTypes.add(new Object[]{fuelName, usageCount});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mostUsedFuelTypes;
    }


}