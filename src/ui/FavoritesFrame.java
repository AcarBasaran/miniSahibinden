package ui;

import dao.*;
import model.*;
import logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class FavoritesFrame extends JFrame {
    private final int userId;
    private JTable table;
    private DefaultTableModel tableModel;


    public FavoritesFrame(int userId) {
        this.userId = userId;
        setTitle("My Favorites");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initTable();

        loadFavorites();

        setVisible(true);

    }



    private void initTable() {
        String[] columns = {"ID", "Brand", "Model", "Category", "Fuel", "Year", "Engine", "Price", "City"};

        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);


    }

    private void loadFavorites() {
        try {
            CarFilterLogic logic = new CarFilterLogic();

            List<Object[]> rows = logic.getFavoriteTableRows(userId);

            tableModel.setRowCount(0);
            for (Object row : rows) {
                tableModel.addRow((Object[]) row);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
