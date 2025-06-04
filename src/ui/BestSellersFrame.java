package ui;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.FuelTypeDAO;
import dao.ModelDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BestSellersFrame extends JFrame {

    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final ModelDAO modelDAO = new ModelDAO();

    public BestSellersFrame() throws Exception {
        setTitle("Best Sellers");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Fuel Types", createTablePanel(fuelTypeDAO.getFuelTypesUsageStats(), new String[]{"Fuel Type", "Usage"}));
        tabs.addTab("Categories", createTablePanel(categoryDAO.getCategoryUsageStats(), new String[]{"Category", "Usage"}));
        tabs.add("Brands", createTablePanel(brandDAO.getBrandStats(), new String[]{"Brand", "Average Price"}));
        tabs.add("Models", createTablePanel(modelDAO.getModelStats(), new String[]{"Model", "Like Count"}));
        // tabs.addTab("Models", createTablePanel(...));

        add(tabs);
        setVisible(true);
    }

    private JPanel createTablePanel(List<Object[]> data, String[] columns) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Object[] row : data) model.addRow(row);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}
