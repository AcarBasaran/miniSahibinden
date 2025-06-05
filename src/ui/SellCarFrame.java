package ui;

import dao.BrandDAO;
import dao.CarDAO;
import dao.ModelDAO;
import model.Brand;
import model.Car;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SellCarFrame extends JFrame {
    private final int userId;
    private final BrandDAO brandDAO = new BrandDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final CarDAO carDAO = new CarDAO();

    private JComboBox<String> brandBox;
    private JComboBox<String> modelBox;
    private JTextField mileageField, yearField, priceField;

    private List<Brand> brandList;
    private List<Model> currentModelList;

    public SellCarFrame(int userId) {
        this.userId = userId;
        setTitle("Sell a Car");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        brandBox = new JComboBox<>();
        modelBox = new JComboBox<>();
        mileageField = new JTextField();
        yearField = new JTextField();
        priceField = new JTextField();

        add(new JLabel("Brand:"));
        add(brandBox);
        add(new JLabel("Model:"));
        add(modelBox);
        add(new JLabel("Mileage:"));
        add(mileageField);
        add(new JLabel("Year:"));
        add(yearField);
        add(new JLabel("Price:"));
        add(priceField);

        JButton submitBtn = new JButton("Sell");
        submitBtn.addActionListener(e -> submitCar());
        add(new JLabel());
        add(submitBtn);

        loadBrands();

        brandBox.addActionListener(e -> loadModelsForSelectedBrand());

        setVisible(true);
    }

    private void loadBrands() {
        try {
            brandList = brandDAO.getAllBrands();
            brandBox.removeAllItems();
            for (Brand brand : brandList) {
                brandBox.addItem(brand.getBrandName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadModelsForSelectedBrand() {
        try {
            int selectedIndex = brandBox.getSelectedIndex();
            if (selectedIndex >= 0) {
                int brandId = brandList.get(selectedIndex).getBrandId();
                currentModelList = modelDAO.getModelsByBrandId(brandId);
                modelBox.removeAllItems();
                for (Model model : currentModelList) {
                    modelBox.addItem(model.getModelName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void submitCar() {
        try {
            int selectedModelIndex = modelBox.getSelectedIndex();
            if (selectedModelIndex < 0) {
                JOptionPane.showMessageDialog(this, "Please select a model.");
                return;
            }

            int modelId = currentModelList.get(selectedModelIndex).getModelId();
            int mileage = Integer.parseInt(mileageField.getText());
            int year = Integer.parseInt(yearField.getText());
            double price = Double.parseDouble(priceField.getText());

            carDAO.addCar(new Car(0, modelId, userId, price, year, mileage, java.time.LocalDate.now().toString()));
            JOptionPane.showMessageDialog(this, "Car listed successfully!");
            dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to list car.");
        }
    }
}
