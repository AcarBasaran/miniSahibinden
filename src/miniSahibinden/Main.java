package miniSahibinden;

import logic.CarFilterLogic;
import model.Car;
import model.Model;
import model.Brand;
import model.FuelType;
import model.Category;
import model.User;
import model.Location;
import dao.*;
import ui.*;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AuthFrame::new);    }
}
