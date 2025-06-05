package ui;

import dao.LocationDAO;
import dao.UserDAO;
import model.Location;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisterPanel extends JPanel {
    private AuthFrame parentFrame;
    private final UserDAO userDAO = new UserDAO();
    private final LocationDAO locationDAO = new LocationDAO();

    private JComboBox<String> cityBox;
    private JTextField nameField, emailField, phoneField;
    private JPasswordField passwordField;

    private List<Location> locationList;

    public RegisterPanel(AuthFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        cityBox = new JComboBox<>();
        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        passwordField = new JPasswordField();


        loadCities();

        panel.add(new JLabel("Name"));
        panel.add(nameField);
        panel.add(new JLabel("Email"));
        panel.add(emailField);
        panel.add(new JLabel("Phone"));
        panel.add(phoneField);
        panel.add(new JLabel("Password"));
        panel.add(passwordField);
        panel.add(new JLabel("City"));
        panel.add(cityBox);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerUser());
        registerButton.setBackground(UIManager.getColor("Button.background"));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> parentFrame.showPanel("Login"));

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));

        btnPanel.add(backButton);
        btnPanel.add(registerButton);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);


    }

    private void loadCities() {
        try {
            locationList = locationDAO.getAllLocations();
            cityBox.removeAllItems();
            for (Location location : locationList) {
                cityBox.addItem(location.getCityName());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void registerUser() {
        try {
            Integer selectedCityIndex = cityBox.getSelectedIndex();

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            Long phone = Long.valueOf(phoneField.getText().trim());
            String password = passwordField.getText().trim();
            Integer locationId = locationList.get(selectedCityIndex).getLocationId();

            User user = new User(0, name, email, password, phone, locationId);
            userDAO.addUser(user);

            JOptionPane.showMessageDialog(this, "Registration Successful");
            parentFrame.showPanel("Login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
