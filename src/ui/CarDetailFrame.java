package ui;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CarDetailFrame extends JFrame {

    DecimalFormat df = new DecimalFormat("#,###");


    private final CarDAO carDAO = new CarDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final LocationDAO locationDAO = new LocationDAO();
    private final UserDAO userDAO = new UserDAO();
    private final FavoriteDAO favoriteDAO = new FavoriteDAO();

    public CarDetailFrame(int userId, int carId) {
        setTitle("Car Details - ID: " + carId);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 5, 5));

        try {
            Car car = carDAO.getCarById(carId);
            Model model = modelDAO.getModelById(car.getModelId());
            Brand brand = brandDAO.getBrandById(model.getBrandId());
            Category category = categoryDAO.getCategoryById(model.getCategoryId());
            FuelType fuelType = fuelTypeDAO.getFuelTypeById(model.getFuelTypeId());
            User owner = userDAO.getUserById(car.getUserId());
            Location city = locationDAO.getLocationById(owner.getLocationId());

            add(new JLabel("Brand: " + brand.getBrandName()));
            add(new JLabel("Model: " + model.getModelName()));
            add(new JLabel("Category: " + category.getCategoryName()));
            add(new JLabel("Fuel Type: " + fuelType.getFuelTypeName()));
            add(new JLabel("Year: " + car.getYear()));
            add(new JLabel("Engine Size: " + model.getEngineCapacity()));
            add(new JLabel("Mileage: " + car.getMileage()));
            add(new JLabel("Price: " + df.format(car.getPrice())));
            add(new JLabel("City: " + city.getCityName()));
            add(new JLabel("Posted by: " + owner.getEmail()));
            add(new JLabel("Owner contact: " + owner.getPhoneNumber()));

            JButton favBtn = new JButton("Add to Favorites");
            favBtn.addActionListener(e -> {
                try {
                    favoriteDAO.addFavorite(userId, carId);
                    JOptionPane.showMessageDialog(this, "Added to favorites!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Already favorited or failed.");
                }
            });
            add(favBtn);

        } catch (Exception e) {
            e.printStackTrace();
            add(new JLabel("Failed to load car details."));
        }

        setVisible(true);
    }
}
