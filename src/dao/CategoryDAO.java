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
    public Category getCategoryById(int categoryId) throws Exception {
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
            ;
        }
        return category;
    }

    public List<Category> getAllCategories() throws SQLException {
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
}