package ui;

import dao.*;
import logic.CarFilterLogic;
import model.Brand;
import model.Category;
import model.FuelType;
import model.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final int userId;
    private JComboBox<String> brandBox;
    private JComboBox<String> cityBox;
    private JComboBox<String> fuelBox;
    private JComboBox<String> categoryBox;
    private JTextField yearField, priceField, mileageField, engineSizeField;

    private JTable resultTable;
    private DefaultTableModel tableModel;

    private final ModelDAO modelDAO = new ModelDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final UserDAO userDAO = new UserDAO();
    private final LocationDAO locationDAO = new LocationDAO();

    public MainFrame(int userId) {
        this.userId = userId;
        setTitle("MiniSahibinden");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initFilterPanel();
        initResultTable();

        setVisible(true);
    }

    private void initFilterPanel() {
        JPanel filterPanel = new JPanel(new GridLayout(4, 4, 10, 5));

        brandBox = new JComboBox<>();
        cityBox = new JComboBox<>();
        fuelBox = new JComboBox<>();
        categoryBox = new JComboBox<>();
        ;
        yearField = new JTextField();
        priceField = new JTextField();
        mileageField = new JTextField();
        engineSizeField = new JTextField();

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> search());

        JButton favoriteBtn = new JButton("Favorites");
        favoriteBtn.addActionListener(e -> openFavorites());

        JButton bestSellerBtn = new JButton("Statistics");
        bestSellerBtn.addActionListener(e -> {
            try {
                openBestSellers();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton sellCarBtn = new JButton("Sell Car");
        sellCarBtn.addActionListener(e -> openSell());

        filterPanel.add(new JLabel("Brand:"));
        filterPanel.add(brandBox);
        filterPanel.add(new JLabel("Fuel Type:"));
        filterPanel.add(fuelBox);
        filterPanel.add(new JLabel("City:"));
        filterPanel.add(cityBox);
        filterPanel.add(new JLabel("Category:"));
        filterPanel.add(categoryBox);
        filterPanel.add(new JLabel("Year:"));
        filterPanel.add(yearField);
        filterPanel.add(new JLabel("Max Price:"));
        filterPanel.add(priceField);
        filterPanel.add(new JLabel("Max Mileage:"));
        filterPanel.add(mileageField);
        filterPanel.add(new JLabel("Engine Size:"));
        filterPanel.add(engineSizeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        buttonPanel.add(searchBtn);
        buttonPanel.add(favoriteBtn);
        buttonPanel.add(bestSellerBtn);
        buttonPanel.add(sellCarBtn);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(filterPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);


        loadBrands();
        loadCities();
        loadCategories();
        loadFuelTypes();
    }

    private void openBestSellers() throws Exception {
        new BestSellersFrame();
    }

    private void openFavorites() {
        new FavoritesFrame(userId);
    }

    private void openSell() {
        new SellCarFrame(userId);
    }

    private void initResultTable() {
        String[] columns = {"ID", "Brand", "Model", "Category", "Year", "Price", "City"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.SOUTH);

        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && resultTable.getSelectedRow() != -1) {
                    int row = resultTable.getSelectedRow();
                    int carId = (int) tableModel.getValueAt(row, 0);
                    new CarDetailFrame(userId, carId);
                }
            }
        });

    }

    private void loadBrands() {
        try {
            List<Brand> brands = brandDAO.getAllBrands();
            brandBox.addItem("Any");
            for (Brand b : brands) brandBox.addItem(b.getBrandName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCities() {
        try {
            List<Location> cities = locationDAO.getAllLocations();
            cityBox.addItem("Any");
            for (Location loc : cities) cityBox.addItem(loc.getCityName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCategories() {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            categoryBox.addItem("Any");
            for (Category c : categories) categoryBox.addItem(c.getCategoryName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFuelTypes() {
        try {
            List<FuelType> fuelTypes = fuelTypeDAO.getAllFuelTypes();
            fuelBox.addItem("Any");
            for (FuelType f : fuelTypes) fuelBox.addItem(f.getFuelTypeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search() {
        try {
            CarFilterLogic logic = new CarFilterLogic();

            String brand = brandBox.getSelectedItem().equals("Any") ? null : brandBox.getSelectedItem().toString();
            String city = cityBox.getSelectedItem().equals("Any") ? null : cityBox.getSelectedItem().toString();
            String fuel = fuelBox.getSelectedItem().equals("Any") ? null : fuelBox.getSelectedItem().toString();
            String category = categoryBox.getSelectedItem().equals("Any") ? null : categoryBox.getSelectedItem().toString();

            Integer year = yearField.getText().isEmpty() ? null : Integer.parseInt(yearField.getText());
            Integer mileage = mileageField.getText().isEmpty() ? null : Integer.parseInt(mileageField.getText());
            Double price = priceField.getText().isEmpty() ? null : Double.parseDouble(priceField.getText());
            Double engineSize = engineSizeField.getText().isEmpty() ? null : Double.parseDouble(engineSizeField.getText());

            List<Object[]> rows = logic.getFilteredTableRows(brand, category, fuel, engineSize, year, mileage, price, city);

            tableModel.setRowCount(0);
            for (Object[] row : rows) {
                tableModel.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
