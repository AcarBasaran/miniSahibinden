package ui;

import dao.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class ListingsPanel extends JPanel {

    private final int userId;
    private final CarDAO carDAO = new CarDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final UserDAO userDAO = new UserDAO();
    private final LocationDAO locationDAO = new LocationDAO();
    DecimalFormat df = new DecimalFormat("#.###");

    private DefaultTableModel tableModel;

    public ListingsPanel(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());

        initTable();
        loadMyCars();
    }

    private void initTable() {
        String[] columns = {"ID", "Brand", "Model", "Category", "Year", "Mileage", "Price", "City", "Date Posted"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadMyCars() {
        try {
            List<Car> cars = carDAO.getCarsByUserId(userId);
            tableModel.setRowCount(0);

            for (Car car : cars) {
                Model model = modelDAO.getModelById(car.getModelId());
                Brand brand = brandDAO.getBrandById(model.getBrandId());
                Category cat = categoryDAO.getCategoryById(model.getCategoryId());
                User user = userDAO.getUserById(car.getUserId());
                Location loc = locationDAO.getLocationById(user.getLocationId());

                tableModel.addRow(new Object[]{
                        car.getCarId(),
                        brand.getBrandName(),
                        model.getModelName(),
                        cat.getCategoryName(),
                        car.getYear(),
                        car.getMileage(),
                        df.format(car.getPrice()),
                        loc.getCityName(),
                        car.getDatePosted()
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
