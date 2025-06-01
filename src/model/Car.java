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

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }


}
