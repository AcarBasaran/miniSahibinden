package dao;

import miniSahibinden.DBConnection;
import model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public List<Car> getCarsByFilter(Integer year, Integer mileage,
                                     Double price) throws SQLException {

        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();

        StringBuilder sql = new StringBuilder("SELECT * FROM Cars WHERE 1=1 ");

        if (year != null) sql.append("AND year = ").append(year).append(" ");
        if (mileage != null) sql.append("AND mileage <= ").append(mileage).append(" ");
        if (price != null) sql.append("AND price <= ").append(price).append(" ");

        ResultSet rs = stmt.executeQuery(sql.toString());

        List<Car> cars = new ArrayList<>();
        while (rs.next()) {
            cars.add(new Car(
                    rs.getInt("car_id"),
                    rs.getInt("model_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("price"),
                    rs.getInt("year"),
                    rs.getInt("mileage"),
                    rs.getString("date_posted")
            ));
        }

        return cars;
    }
}
