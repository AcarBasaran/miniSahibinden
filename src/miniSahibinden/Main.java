package miniSahibinden;

import logic.CarFilterLogic;
import model.Car;
import model.Model;
import model.Brand;
import model.FuelType;
import model.Category;
import model.User;
import model.Location;
import dao.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CarFilterLogic filterLogic = new CarFilterLogic();

            List<Car> filteredCars = filterLogic.filterCars(
                    "BMW",     // brand
                    null,      // category
                    null,      // fuel
                    null,      // engineCapacity
                    null,      // year
                    null,      // mileage
                    null,      // price
                    null       // city
            );

            ModelDAO modelDAO = new ModelDAO();
            BrandDAO brandDAO = new BrandDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
            UserDAO userDAO = new UserDAO();
            LocationDAO locationDAO = new LocationDAO();

            for (Car car : filteredCars) {
                Model model = modelDAO.getModelById(car.getModelId());
                Brand brand = brandDAO.getBrandById(model.getBrandId());
                Category category = categoryDAO.getCategoryById(model.getCategoryId());
                FuelType fuelType = fuelTypeDAO.getFuelTypeById(model.getFuelTypeId());
                User user = userDAO.getUserById(car.getUserId());
                Location location = locationDAO.getLocationById(user.getLocationId());

                System.out.println("Car ID: " + car.getCarId() +
                        ", Brand: " + brand.getBrandName() +
                        ", Model: " + model.getModelName() +
                        ", Category: " + category.getCategoryName() +
                        ", Fuel: " + fuelType.getFuelTypeName() +
                        ", Engine: " + model.getEngineCapacity() +
                        ", Price: " + car.getPrice() +
                        ", Year: " + car.getYear() +
                        ", City: " + location.getCityName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
