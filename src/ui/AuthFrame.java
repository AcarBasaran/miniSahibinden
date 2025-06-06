package ui;

import javax.swing.*;
import java.awt.*;


public class AuthFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public AuthFrame() {
        setTitle("MiniSahibinden");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(400, 250);

        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(this);
        RegisterPanel registerPanel = new RegisterPanel(this);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registerPanel, "Register");

        add(cardPanel);
        setVisible(true);


    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}
