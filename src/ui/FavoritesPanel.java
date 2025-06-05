package ui;

import logic.CarFilterLogic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FavoritesPanel extends JPanel {
    private final int userId;
    private JTable table;
    private DefaultTableModel tableModel;


    public FavoritesPanel(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());


        initTable();

        loadFavorites();

        setVisible(true);

    }


    private void initTable() {
        String[] columns = {"ID", "Brand", "Model", "Category", "Year", "Milage", "Price", "City"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    int carId = (int) tableModel.getValueAt(row, 0);
                    new CarDetailFrame(userId, carId);
                }
            }
        });


    }

    private void loadFavorites() {
        try {
            CarFilterLogic logic = new CarFilterLogic();

            List<Object[]> rows = logic.getFavoriteTableRows(userId);

            tableModel.setRowCount(0);
            for (Object row : rows) {
                tableModel.addRow((Object[]) row);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
