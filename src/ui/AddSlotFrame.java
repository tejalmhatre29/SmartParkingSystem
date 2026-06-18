package ui;

import dao.ParkingSlotDAO;
import model.ParkingSlot;

import java.awt.*;
import javax.swing.*;

public class AddSlotFrame extends JFrame {

    public AddSlotFrame() {

        setTitle("Add Parking Slot");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("ADD PARKING SLOT");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(130, 20, 250, 30);

        JLabel slotLabel = new JLabel("Slot Number");
        slotLabel.setBounds(50, 90, 120, 25);

        JTextField slotField = new JTextField();
        slotField.setBounds(180, 90, 200, 30);

        JLabel typeLabel = new JLabel("Vehicle Type");
        typeLabel.setBounds(50, 150, 120, 25);

        String[] types = {"Car", "Bike", "Truck", "Bus"};
        JComboBox<String> typeBox = new JComboBox<>(types);
        typeBox.setBounds(180, 150, 200, 30);

        JButton addBtn = new JButton("Add Slot");
        addBtn.setBounds(170, 250, 140, 40);

        addBtn.addActionListener(e -> {

            String slotNumber =
                    slotField.getText().trim();

            String vehicleType =
                    typeBox.getSelectedItem().toString();

            if(slotNumber.isEmpty()) {

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

            if(added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Slot Added Successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed To Add Slot!"
                );
            }

        });

        panel.add(title);
        panel.add(slotLabel);
        panel.add(slotField);
        panel.add(typeLabel);
        panel.add(typeBox);
        panel.add(addBtn);

        add(panel);

        setVisible(true);
    }
}