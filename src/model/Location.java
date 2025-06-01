package model;

public class Location {
    private int locationId;
    private String cityName;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Location(int locationId, String cityName) {
        this.locationId = locationId;
        this.cityName = cityName;
    }
}
