package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class AdminDashboard extends JFrame {

    private final Color PURPLE = new Color(155, 89, 182);
    private final Color LIGHT_PURPLE = new Color(240, 230, 255);

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(PURPLE);
        title.setBounds(320, 30, 400, 40);

        // Subtitle
        JLabel subtitle = new JLabel(
                "Manage parking slots, users, vehicles and reports"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(270, 75, 500, 25);

        // Main Dashboard Card
        JPanel card = new JPanel(null);
        card.setBounds(75, 130, 850, 420);
        card.setBackground(Color.WHITE);
        card.setBorder(
                new LineBorder(
                        new Color(220, 220, 220),
                        1,
                        true
                )
        );

        // Buttons
        JButton manageSlotsBtn =
                createButton("🅿 Manage Slots");

        JButton viewSlotsBtn =
                createButton("📋 View Slots");

        JButton viewUsersBtn =
                createButton("👥 View Users");

        JButton vehiclesBtn =
                createButton("🚗 View Vehicles");

        JButton revenueBtn =
                createButton("💰 Revenue Report");

        JButton logoutBtn =
                createButton("🚪 Logout");

        manageSlotsBtn.setBounds(60, 60, 280, 90);
        viewSlotsBtn.setBounds(500, 60, 280, 90);

        viewUsersBtn.setBounds(60, 220, 280, 90);
        vehiclesBtn.setBounds(500, 220, 280, 90);

        revenueBtn.setBounds(60, 340, 280, 50);
        logoutBtn.setBounds(500, 340, 280, 50);

        // Actions
        manageSlotsBtn.addActionListener(e -> {
            new AddSlotFrame();
        });

        viewSlotsBtn.addActionListener(e -> {
            new ViewSlotsFrame();
        });

        viewUsersBtn.addActionListener(e -> {
            new ViewUsersFrame();
        });

        vehiclesBtn.addActionListener(e -> {
            new ViewVehiclesFrame();
        });

        revenueBtn.addActionListener(e -> {
            new RevenueReportFrame();
        });

        logoutBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        // Add Buttons to Card
        card.add(manageSlotsBtn);
        card.add(viewSlotsBtn);
        card.add(viewUsersBtn);
        card.add(vehiclesBtn);
        card.add(revenueBtn);
        card.add(logoutBtn);

        // Add Components
        panel.add(title);
        panel.add(subtitle);
        panel.add(card);

        add(panel);

        setVisible(true);
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        button.setBackground(PURPLE);
        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        // Hover Effect
        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                new Color(
                                        175,
                                        110,
                                        200
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                PURPLE
                        );
                    }
                });

        return button;
    }
}