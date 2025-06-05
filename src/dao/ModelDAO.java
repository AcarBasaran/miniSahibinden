package dao;

import miniSahibinden.DBConnection;
import model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModelDAO {
    public Model getModelById(int modelId) throws Exception {
        String sql = "SELECT * FROM Models WHERE model_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, modelId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Model(rs.getInt("model_id"), rs.getInt("brand_id"), rs.getInt("category_id"), rs.getString("model_name"), rs.getInt("fuel_type_id"), rs.getDouble("engine_capacity"));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Model> getModelsByBrandId(int brandId) throws Exception {
        String sql = "SELECT * FROM Models WHERE brand_id = ?";

        List<Model> modelsByBrand = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, brandId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modelsByBrand.add(new Model(rs.getInt("model_id"), rs.getInt("brand_id"), rs.getInt("category_id"), rs.getString("model_name"), rs.getInt("fuel_type_id"), rs.getDouble("engine_capacity")));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelsByBrand;
    }

    public List<Object[]> getModelStats() {
        String sql = """
                SELECT Models.model_name, COUNT(*) AS favCount
                FROM Favorites 
                JOIN Cars ON Favorites.car_id = Cars.car_id
                JOIN Models ON Cars.model_id = Models.model_id
                GROUP BY Models.model_name
                ORDER BY favCount DESC
                """;
        List<Object[]> result = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String modelName = rs.getString("model_name");
                int favCount = rs.getInt("favCount");
                result.add(new Object[]{modelName, favCount});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

}
