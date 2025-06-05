package model;

public class Location {
    private int locationId;
    private String cityName;

    public int getLocationId() {
        return locationId;
    }


    public String getCityName() {
        return cityName;
    }


    public Location(int locationId, String cityName) {
        this.locationId = locationId;
        this.cityName = cityName;
    }
}
