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

public class SellPanel extends JPanel {
    private final int userId;
    private final BrandDAO brandDAO = new BrandDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final CarDAO carDAO = new CarDAO();

    private JComboBox<String> brandBox;
    private JComboBox<String> modelBox;
    private JTextField mileageField, yearField, priceField;

    private List<Brand> brandList;
    private List<Model> currentModelList;

    public SellPanel(int userId) {
        this.userId = userId;

        initSellPanel();
        setVisible(true);
    }

    private void initSellPanel() {
        JPanel sellPanel = new JPanel(new GridLayout(6, 2));


        brandBox = new JComboBox<>();
        modelBox = new JComboBox<>();
        mileageField = new JTextField();
        yearField = new JTextField();
        priceField = new JTextField();


        sellPanel.add(new JLabel("Brand:"));
        sellPanel.add(brandBox);
        sellPanel.add(new JLabel("Model:"));
        sellPanel.add(modelBox);
        sellPanel.add(new JLabel("Mileage:"));
        sellPanel.add(mileageField);
        sellPanel.add(new JLabel("Year:"));
        sellPanel.add(yearField);
        sellPanel.add(new JLabel("Price:"));
        sellPanel.add(priceField);

        loadBrands();
        brandBox.addActionListener(e -> loadModelsForSelectedBrand());


        JButton submitBtn = new JButton("Sell");
        submitBtn.addActionListener(e -> submitCar());

        sellPanel.add(new JLabel(""));
        sellPanel.add(submitBtn);

        add(sellPanel);


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

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to list car.");
        }
    }
}
