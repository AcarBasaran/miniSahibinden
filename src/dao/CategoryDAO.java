package dao;

import miniSahibinden.DBConnection;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryDAO {
    public Category getCategoryById(int categoryId) throws Exception {
        String sql = "select * from Categories where category_id=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        sql
                );
        ) {
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
}