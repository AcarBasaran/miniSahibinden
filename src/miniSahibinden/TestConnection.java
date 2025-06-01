package miniSahibinden;

import dao.*;
import model.*;

import java.util.List;

public class TestConnection {
    public static void main(String[] args) {
        try {
            CarDAO carDAO = new CarDAO();
            ModelDAO modelDAO = new ModelDAO();
            BrandDAO brandDAO = new BrandDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
            UserDAO userDAO = new UserDAO();
            LocationDAO locationDAO = new LocationDAO();

            List<Car> cars = carDAO.getCarsByFilter(2015, null, null);

            for (Car car : cars) {
                Model model = modelDAO.getModelById(car.getModelId());
                Brand brand = brandDAO.getBrandById(model.getBrandId());
                FuelType fuelType = fuelTypeDAO.getFuelTypeById(model.getFuelTypeId());
                Category category = categoryDAO.getCategoryById(model.getCategoryId());
                User user = userDAO.getUserById(car.getUserId());
                Location location = locationDAO.getLocationById(user.getLocationId());

                System.out.println("Car ID: " + car.getCarId() +
                        ", Brand: " + brand.getBrandName() +
                        ", Model: " + model.getModelName() +
                        ", Category: " + category.getCategoryName() +
                        ", Fuel: " + fuelType.getFuelTypeName() +
                        ", City: " + location.getCityName() +
                        ", Price: " + car.getPrice() +
                        ", Year: " + car.getYear());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
