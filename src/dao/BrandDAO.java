package dao;

import miniSahibinden.DBConnection;
import model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    public Brand getBrandById(int brandId) throws Exception {
        String sql = "SELECT * FROM Brands WHERE brand_id = ?";
        Brand brand = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, brandId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                brand = new Brand(rs.getInt("brand_id"), rs.getString("brand_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand;
    }

    public List<Brand> getAllBrands() throws SQLException {
        String sql = "SELECT * FROM Brands";
        List<Brand> brands = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                brands.add(new Brand(rs.getInt("brand_id"), rs.getString("brand_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } return brands;

    }
}