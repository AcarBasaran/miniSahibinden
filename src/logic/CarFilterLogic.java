package logic;

import dao.*;
import model.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarFilterLogic {

    DecimalFormat df = new DecimalFormat("#,###");

    private final CarDAO carDAO = new CarDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final UserDAO userDAO = new UserDAO();
    private final LocationDAO locationDAO = new LocationDAO();
    private final FavoriteDAO favoriteDAO = new FavoriteDAO();


    public List<Car> filterCars(String brand, String category, String fuel,
                                Double engineCapacity, Integer year, Integer mileage,
                                Double price, String city) throws Exception {

        List<Car> allCars = carDAO.getAllCars();
        List<Car> result = new ArrayList<>();

        for (Car car : allCars) {
            Model model = modelDAO.getModelById(car.getModelId());
            Brand b = brandDAO.getBrandById(model.getBrandId());
            Category c = categoryDAO.getCategoryById(model.getCategoryId());
            FuelType f = fuelTypeDAO.getFuelTypeById(model.getFuelTypeId());
            User user = userDAO.getUserById(car.getUserId());
            Location loc = locationDAO.getLocationById(user.getLocationId());

            boolean match =
                    (brand == null || b.getBrandName().equals(brand)) &&
                            (category == null || c.getCategoryName().equals(category)) &&
                            (fuel == null || f.getFuelTypeName().equals(fuel)) &&
                            (engineCapacity == null || model.getEngineCapacity() <= engineCapacity) &&
                            (year == null || car.getYear() >= year) &&
                            (mileage == null || car.getMileage() <= mileage) &&
                            (price == null || car.getPrice() <= price) &&
                            (city == null || loc.getCityName().equals(city));

            if (match) result.add(car);
        }

        return result;
    }

    public List<Object[]> getFilteredTableRows(String brand, String category, String fuel,
                                               Double engineCapacity, Integer year, Integer mileage,
                                               Double price, String city) throws Exception {
        List<Car> cars = filterCars(brand, category, fuel, engineCapacity, year, mileage, price, city);
        List<Object[]> rows = new ArrayList<>();

        for (Car car : cars) {
            Model model = modelDAO.getModelById(car.getModelId());
            Brand b = brandDAO.getBrandById(model.getBrandId());
            Category c = categoryDAO.getCategoryById(model.getCategoryId());
            FuelType f = fuelTypeDAO.getFuelTypeById(model.getFuelTypeId());
            User user = userDAO.getUserById(car.getUserId());
            Location loc = locationDAO.getLocationById(user.getLocationId());

            rows.add(new Object[]{
                    car.getCarId(),
                    b.getBrandName(),
                    model.getModelName(),
                    c.getCategoryName(),
                    f.getFuelTypeName(),
                    car.getYear(),
                    model.getEngineCapacity(),
                    df.format(car.getPrice()),
                    loc.getCityName()
            });
        }

        return rows;
    }

    public List<Object[]> getFavoriteTableRows(int userId) throws Exception {
        List<Car> cars = favoriteDAO.getFavoriteCarsByUserId(userId);
        List<Object[]> rows = new ArrayList<>();

        for(Car car : cars) {
            Model model = modelDAO.getModelById(car.getModelId());
            Brand b = brandDAO.getBrandById(model.getBrandId());
            Category c = categoryDAO.getCategoryById(model.getCategoryId());
            FuelType f = fuelTypeDAO.getFuelTypeById(model.getFuelTypeId());
            User user = userDAO.getUserById(car.getUserId());
            Location loc = locationDAO.getLocationById(user.getLocationId());

            rows.add(new Object[]{
                    car.getCarId(),
                    b.getBrandName(),
                    model.getModelName(),
                    c.getCategoryName(),
                    f.getFuelTypeName(),
                    car.getYear(),
                    model.getEngineCapacity(),
                    df.format(car.getPrice()),
                    loc.getCityName()
            });
        }
        return rows;
    }

}
