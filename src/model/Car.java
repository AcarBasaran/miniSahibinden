package model;

public class Car {
    private int carId, modelId, userId, year, mileage;
    private double price;
    private String datePosted;

    public Car(int carId, int modelId, int userId, double price, int year, int mileage, String datePosted) {
        this.carId = carId;
        this.modelId = modelId;
        this.userId = userId;
        this.price = price;
        this.year = year;
        this.mileage = mileage;
        this.datePosted = datePosted;
    }

    public int getCarId() {
        return carId;
    }


    public int getModelId() {
        return modelId;
    }


    public int getUserId() {
        return userId;
    }


    public int getYear() {
        return year;
    }


    public int getMileage() {
        return mileage;
    }


    public double getPrice() {
        return price;
    }


    public String getDatePosted() {
        return datePosted;
    }


}
