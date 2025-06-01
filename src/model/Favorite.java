package model;

public class Favorite {
    private int userId;
    private int carId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Favorite (int userId, int carId) {
        this.userId = userId;
        this.carId = carId;
    }
}
