package ui;

import dao.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StatisticsPanel extends JPanel {

    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final LocationDAO locationDAO = new LocationDAO();

    public StatisticsPanel()  {
        setLayout(new BorderLayout());


        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Fuel Types", createTablePanel(fuelTypeDAO.getFuelTypesUsageStats(), new String[]{"Fuel Type", "Usage"}));
        tabs.addTab("Categories", createTablePanel(categoryDAO.getCategoryUsageStats(), new String[]{"Category", "Usage"}));
        tabs.add("Brands", createTablePanel(brandDAO.getBrandStats(), new String[]{"Brand", "Average Price"}));
        tabs.add("Models", createTablePanel(modelDAO.getModelStats(), new String[]{"Model", "Like Count"}));
        tabs.add("Locations", createTablePanel(locationDAO.getLocationStats(), new String[]{"Location", "Car Count"}));
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
