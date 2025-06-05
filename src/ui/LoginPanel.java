package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final UserDAO userDAO = new UserDAO();
    private final AuthFrame parentFrame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(AuthFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> login());

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> parentFrame.showPanel("Register"));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 1));


        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        try {
            User user = userDAO.getUserByEmailAndPassword(email, password);
            if (user != null) {
                JOptionPane.showMessageDialog(parentFrame, "You have successfully logged in!");
                parentFrame.dispose();
                new MainFrame(user.getUserId());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Login failed.");
        }
    }


}
