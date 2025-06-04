package ui;

import logic.CarFilterLogic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BestSellersFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public BestSellersFrame() {
        setTitle("Best Sellers");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        setVisible(true);

        initTable();
        loadMostUsedFuelType();

    }

    private void initTable() {
        String[] columns = {"Fuel Type", "Usage"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // 🔒 disables editing for all cells
            }
        };

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void loadMostUsedFuelType() {
        try {
            CarFilterLogic logic = new CarFilterLogic();

            List<Object[]> rows = logic.getFuelTypeRows();

            tableModel.setRowCount(0);
            for (Object row : rows) {
                tableModel.addRow((Object[]) row);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
