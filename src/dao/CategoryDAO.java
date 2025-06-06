package dao;

import miniSahibinden.DBConnection;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM Categories WHERE category_id=?";
        Category category = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                category = new Category(rs.getInt("category_id"), rs.getString("name"));
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return category;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Categories";
        List<Category> categories = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                categories.add(new Category(rs.getInt("category_id"), rs.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Object[]> getCategoryUsageStats() {
        String sql = """
                SELECT Categories.name, COUNT(*) AS usage_count
                FROM Cars 
                JOIN Models ON Cars.model_id = Models.model_id 
                JOIN Categories ON Models.category_id = Categories.category_id
                GROUP BY Categories.name
                ORDER BY usage_count DESC
                """;
        List<Object[]> result = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("name");
                int usageCount = rs.getInt("usage_count");
                result.add(new Object[]{categoryName, usageCount});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}