package ui;

import dao.ParkingSlotDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.ParkingSlot;

public class AddSlotFrame extends JFrame {

    private final Color PURPLE = new Color(155, 89, 182);
    private final Color LIGHT_PURPLE = new Color(240, 230, 255);

    public AddSlotFrame() {

        setTitle("Add Parking Slot");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title = new JLabel("ADD PARKING SLOT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(PURPLE);
        title.setBounds(160, 35, 300, 40);

        // Subtitle
        JLabel subtitle = new JLabel(
                "Create a new parking slot"
        );
        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(215, 70, 200, 20);

        // White Card
        JPanel card = new JPanel(null);
        card.setBounds(70, 110, 450, 240);
        card.setBackground(Color.WHITE);
        card.setBorder(
                new LineBorder(
                        new Color(220, 220, 220),
                        1,
                        true
                )
        );

        // Slot Number
        JLabel slotLabel = new JLabel("Slot Number");
        slotLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );
        slotLabel.setBounds(40, 50, 120, 30);

        JTextField slotField = new JTextField();
        slotField.setBounds(180, 50, 220, 35);
        slotField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        // Vehicle Type
        JLabel typeLabel = new JLabel("Vehicle Type");
        typeLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );
        typeLabel.setBounds(40, 110, 120, 30);

        String[] types = {
                "Car",
                "Bike",
                "Truck",
                "Bus"
        };

        JComboBox<String> typeBox =
                new JComboBox<>(types);

        typeBox.setBounds(180, 110, 220, 35);
        typeBox.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        // Add Button
        JButton addBtn =
                createButton("Add Slot");

        addBtn.setBounds(
                150,
                180,
                150,
                40
        );

        addBtn.addActionListener(e -> {

            String slotNumber =
                    slotField.getText().trim();

            String vehicleType =
                    typeBox.getSelectedItem().toString();

            if (slotNumber.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Slot Number!"
                );

                return;
            }

            ParkingSlot slot =
                    new ParkingSlot(
                            0,
                            slotNumber,
                            vehicleType,
                            "AVAILABLE"
                    );

            ParkingSlotDAO dao =
                    new ParkingSlotDAO();

            boolean added =
                    dao.addSlot(slot);

            if (added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Slot Added Successfully!"
                );

                slotField.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed To Add Slot!"
                );
            }

        });

        card.add(slotLabel);
        card.add(slotField);
        card.add(typeLabel);
        card.add(typeBox);
        card.add(addBtn);

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
                        15
                )
        );

        button.setBackground(PURPLE);
        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

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