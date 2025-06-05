package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final UserDAO userDAO = new UserDAO();
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - MiniSahibinden");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initUI();
        setVisible(true);
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
        registerBtn.addActionListener(e -> register());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 1));
        add(panel, BorderLayout.CENTER);
        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        try {
            User user = userDAO.getUserByEmailAndPassword(email, password);
            if (user != null) {
                dispose();
                new MainFrame(user.getUserId());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Login failed.");
        }
    }

    private void register() {
        new RegisterFrame();
        LoginFrame.this.dispose();
    }
}
