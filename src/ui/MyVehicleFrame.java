package ui;

import dao.UserDAO;
import util.UserSession;

import javax.swing.*;
import java.awt.*;

public class MyVehicleFrame extends JFrame {

    public MyVehicleFrame() {

        setTitle("My Vehicle");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        UserDAO dao = new UserDAO();

        String[] details =
                dao.getVehicleDetails(
                        UserSession.loggedInEmail
                );

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Name:"));
        panel.add(new JLabel(details[0]));

        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(details[1]));

        panel.add(new JLabel("Vehicle Number:"));
        panel.add(new JLabel(details[2]));

        panel.add(new JLabel("Vehicle Type:"));
        panel.add(new JLabel(details[3]));

        add(panel);

        setVisible(true);
    }
}