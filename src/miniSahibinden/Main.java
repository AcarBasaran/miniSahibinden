package miniSahibinden;

import ui.AuthFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AuthFrame::new);
    }
}
