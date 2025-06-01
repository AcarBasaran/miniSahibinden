package dao;

import miniSahibinden.DBConnection;
import model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
