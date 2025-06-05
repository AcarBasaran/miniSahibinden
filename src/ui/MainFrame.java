package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame(int userId) {
        setTitle("MiniSahibinden");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new SearchPanel(userId), "search");
        mainPanel.add(new StatisticsPanel(), "stats");
        mainPanel.add(new SellPanel(userId), "sell");
        mainPanel.add(new FavoritesPanel(userId), "favorites");
        mainPanel.add(new ListingsPanel(userId), "mylistings");

        JPanel navPanel = new JPanel(new GridLayout(1, 5));

        navPanel.add(createButton("Search", "search"));
        navPanel.add(createButton("Statistics", "stats"));
        navPanel.add(createButton("Sell", "sell"));
        navPanel.add(createButton("Favorites", "favorites"));
        navPanel.add(createButton("My listings", "mylistings"));

        add(navPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);


        setVisible(true);
    }

    private JButton createButton(String label, String panelKey) {
        JButton button = new JButton(label);
        button.addActionListener(e -> cardLayout.show(mainPanel, panelKey));
        return button;
    }


}
