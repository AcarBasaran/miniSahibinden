package model;

public class User {
    private int userId, locationId;
    private long phoneNumber;
    private String userName, email, password;

    public int getUserId() {
        return userId;
    }


    public long getPhoneNumber() {
        return phoneNumber;
    }


    public int getLocationId() {
        return locationId;
    }


    public String getUserName() {
        return userName;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public User(int userId, String userName, String email, String password, long phoneNumber, int locationId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.locationId = locationId;

    }
}
