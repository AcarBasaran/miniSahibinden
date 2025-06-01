package ui;

import dao.BrandDAO;
import dao.LocationDAO;
import logic.CarFilterLogic;
import model.Brand;
import model.Car;
import model.Location;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private JComboBox<String> brandBox;
    private JComboBox<String> cityBox;
    private JComboBox<String> fuelBox;
    private JTextField yearField, priceField, mileageField;

    private JTable resultTable;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("MiniSahibinden");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initFilterPanel();
        initResultTable();

        setVisible(true);
    }

    private void initFilterPanel() {
        JPanel filterPanel = new JPanel(new GridLayout(3, 4, 10, 5));

        brandBox = new JComboBox<>();
        cityBox = new JComboBox<>();
        fuelBox = new JComboBox<>(new String[] { "Any", "Diesel", "Petrol", "LPG", "Electric" });
        yearField = new JTextField();
        priceField = new JTextField();
        mileageField = new JTextField();

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> search());

        filterPanel.add(new JLabel("Brand:"));
        filterPanel.add(brandBox);
        filterPanel.add(new JLabel("Fuel Type:"));
        filterPanel.add(fuelBox);
        filterPanel.add(new JLabel("City:"));
        filterPanel.add(cityBox);
        filterPanel.add(new JLabel("Year:"));
        filterPanel.add(yearField);
        filterPanel.add(new JLabel("Max Price:"));
        filterPanel.add(priceField);
        filterPanel.add(new JLabel("Max Mileage:"));
        filterPanel.add(mileageField);

        add(filterPanel, BorderLayout.NORTH);
        add(searchBtn, BorderLayout.CENTER);

        loadBrands();
        loadCities();
    }

    private void initResultTable() {
        String[] columns = {"ID", "Brand", "Model", "Category", "Fuel", "Year", "Engine", "Price", "City"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.SOUTH);
    }

    private void loadBrands() {
        try {
            BrandDAO dao = new BrandDAO();
            List<Brand> brands = dao.getAllBrands();
            brandBox.addItem("Any");
            for (Brand b : brands) brandBox.addItem(b.getBrandName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCities() {
        try {
            LocationDAO dao = new LocationDAO();
            List<Location> cities = dao.getAllLocations();
            cityBox.addItem("Any");
            for (Location loc : cities) cityBox.addItem(loc.getCityName());
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

            Integer year = yearField.getText().isEmpty() ? null : Integer.parseInt(yearField.getText());
            Integer mileage = mileageField.getText().isEmpty() ? null : Integer.parseInt(mileageField.getText());
            Double price = priceField.getText().isEmpty() ? null : Double.parseDouble(priceField.getText());

            List<Car> results = logic.filterCars(brand, null, fuel, null, year, mileage, price, city);

            tableModel.setRowCount(0);
            for (Car c : results) {
                tableModel.addRow(new Object[]{
                        c.getCarId(), c.getModelId(), "(load model name)", "(load category)",
                        "(load fuel)", c.getYear(), "(load engine)", c.getPrice(), "(load city)"
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
