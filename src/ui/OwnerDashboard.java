package ui;

import java.awt.*;
import javax.swing.*;

public class OwnerDashboard extends JFrame {

    public OwnerDashboard() {

        setTitle("Owner Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("OWNER DASHBOARD");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(300, 20, 350, 40);

        JButton bookSlotBtn = new JButton("Book Parking Slot");
        bookSlotBtn.setBounds(100, 120, 250, 80);

        JButton myVehicleBtn = new JButton("My Vehicle");
        myVehicleBtn.setBounds(500, 280, 250, 80);

        JButton historyBtn = new JButton("Parking History");
        historyBtn.setBounds(100, 280, 250, 80);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(300, 430, 250, 80);

        JButton exitBtn = new JButton("Exit Parking");
        exitBtn.setBounds(500, 120, 250, 80);

        exitBtn.addActionListener(e -> {
            new ExitParkingFrame();
        });

        bookSlotBtn.addActionListener(e -> {
            new BookSlotFrame();
        });

        historyBtn.addActionListener(e -> {
            new ParkingHistoryFrame();
        });
        myVehicleBtn.addActionListener(e -> {
            new MyVehicleFrame();
        });

        panel.add(title);
        panel.add(bookSlotBtn);
        panel.add(myVehicleBtn);
        panel.add(historyBtn);
        panel.add(logoutBtn);
        panel.add(exitBtn);

        add(panel);

        setVisible(true);
    }
}
