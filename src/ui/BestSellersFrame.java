package ui;

import dao.FuelTypeDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BestSellersFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private FuelTypeDAO fuelTypeDAO;

    public BestSellersFrame() {
        this.fuelTypeDAO = new FuelTypeDAO();
        setTitle("Best Sellers");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        initTable();
        loadMostUsedFuelType();

        setVisible(true);


    }

    private void initTable() {
        String[] columns = {"Fuel Type", "Usage"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void loadMostUsedFuelType() {
        try {
            List<Object[]> rows = fuelTypeDAO.getFuelTypesUsageStats();

            tableModel.setRowCount(0);
            for (Object row : rows) {
                tableModel.addRow((Object[]) row);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
