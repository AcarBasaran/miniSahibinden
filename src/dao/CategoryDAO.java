package dao;

import miniSahibinden.DBConnection;
import model.Category;

import java.sql.*;

public class CategoryDAO {
    public Category getCategoryById(int categoryId) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Categories WHERE category_id = ?"
        );
        stmt.setInt(1, categoryId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Category(
                    rs.getInt("category_id"),
                    rs.getString("name")
            );
        }

        return null;
    }
}
