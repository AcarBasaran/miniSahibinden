package model;

public class FuelType {
    private int fuelTypeId;
    private String fuelTypeName;

    public int getFuelTypeId() {
        return fuelTypeId;
    }


    public String getFuelTypeName() {
        return fuelTypeName;
    }


    public FuelType(int fuelTypeId, String fuelTypeName) {
        this.fuelTypeId = fuelTypeId;
        this.fuelTypeName = fuelTypeName;
    }
}
