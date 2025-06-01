package ui;

import javax.swing.SwingUtilities;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
