package dao;

import miniSahibinden.DBConnection;
import model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    public Brand getBrandById(int brandId) {
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

    public List<Brand> getAllBrands() {
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

        }
        return brands;

    }

    public List<Object[]> getBrandStats() {
        String sql = """
                SELECT Brands.brand_name, AVG(Cars.price) AS average_price
                FROM Cars JOIN Models ON Cars.model_id = Models.model_id
                JOIN Brands ON Models.brand_id = Brands.brand_id
                GROUP BY Brands.brand_name
                ORDER BY average_price DESC
                """;
        List<Object[]> result = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String brandName = rs.getString("brand_name");
                int averagePrice = rs.getInt("average_price");
                result.add(new Object[]{brandName, averagePrice});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}