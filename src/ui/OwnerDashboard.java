package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class OwnerDashboard extends JFrame {

    private final Color PURPLE = new Color(155, 89, 182);
    private final Color LIGHT_PURPLE = new Color(240, 230, 255);

    public OwnerDashboard() {

        setTitle("Owner Dashboard");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title = new JLabel("OWNER DASHBOARD");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(PURPLE);
        title.setBounds(310, 30, 400, 40);

        // Subtitle
        JLabel subtitle = new JLabel(
                "Manage your parking bookings and vehicle information"
        );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(250, 75, 500, 25);

        // Main Card
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
        JButton bookSlotBtn
                = createButton("🅿 Book Parking Slot");

        JButton exitBtn
                = createButton("🚪 Exit Parking");

        JButton historyBtn
                = createButton("📋 Parking History");

        JButton myVehicleBtn
                = createButton("🚗 My Vehicle");

        JButton logoutBtn
                = createButton("🔒 Logout");

        // Positions
        bookSlotBtn.setBounds(60, 60, 280, 90);
        exitBtn.setBounds(500, 60, 280, 90);

        historyBtn.setBounds(60, 220, 280, 90);
        myVehicleBtn.setBounds(500, 220, 280, 90);

        logoutBtn.setBounds(280, 340, 280, 50);

        // Actions
        bookSlotBtn.addActionListener(e -> {
            new BookSlotFrame();
        });

        exitBtn.addActionListener(e -> {
            new ExitParkingFrame();
        });

        historyBtn.addActionListener(e -> {
            new ParkingHistoryFrame();
        });

        myVehicleBtn.addActionListener(e -> {
            new MyVehicleFrame();
        });

        logoutBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        // Add Components
        card.add(bookSlotBtn);
        card.add(exitBtn);
        card.add(historyBtn);
        card.add(myVehicleBtn);
        card.add(logoutBtn);

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
