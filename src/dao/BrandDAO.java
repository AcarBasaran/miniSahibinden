package dao;

import miniSahibinden.DBConnection;
import model.Brand;

import java.sql.*;

public class BrandDAO {
    public Brand getBrandById(int brandId) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Brands WHERE brand_id = ?"
        );
        stmt.setInt(1, brandId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Brand(
                    rs.getInt("brand_id"),
                    rs.getString("brand_name")
            );
        }

        return null;
    }
}
