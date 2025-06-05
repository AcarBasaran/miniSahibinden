package ui;

import dao.*;

import model.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterFrame extends JFrame {

    private final UserDAO userDAO = new UserDAO();

    private JComboBox<String> cityBox;
    private JTextField nameField, emailField, phoneField;
    private JPasswordField passwordField;

    private List<Location> locationList;

    public RegisterFrame() {
        setTitle("Register");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadCities(){

    }

    private void registerUser() {

    }
}
