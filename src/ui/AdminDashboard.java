package ui;

import java.awt.*;
import javax.swing.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(330, 20, 350, 40);

        // Buttons
        JButton manageSlotsBtn = new JButton("Manage Slots");
        manageSlotsBtn.setBounds(100, 120, 250, 80);

        JButton viewSlotsBtn = new JButton("View Slots");
        viewSlotsBtn.setBounds(600, 120, 250, 80);

        JButton viewUsersBtn = new JButton("View Users");
        viewUsersBtn.setBounds(100, 280, 250, 80);

        JButton vehiclesBtn = new JButton("View Vehicles");
        vehiclesBtn.setBounds(600, 280, 250, 80);

        JButton revenueBtn = new JButton("Revenue Report");
        revenueBtn.setBounds(100, 440, 250, 80);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(600, 440, 250, 80);

        // Actions
        manageSlotsBtn.addActionListener(e -> {
            new AddSlotFrame();
        });

        viewSlotsBtn.addActionListener(e -> {
            new ViewSlotsFrame();
        });

        logoutBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        // Add Components
        panel.add(title);
        panel.add(manageSlotsBtn);
        panel.add(viewSlotsBtn);
        panel.add(viewUsersBtn);
        panel.add(vehiclesBtn);
        panel.add(revenueBtn);
        panel.add(logoutBtn);

        add(panel);

        setVisible(true);
    }
}