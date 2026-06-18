package ui;

import dao.ParkingSlotDAO;
import model.ParkingSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewSlotsFrame extends JFrame {

    public ViewSlotsFrame() {

        setTitle("View Parking Slots");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "ID",
                "Slot Number",
                "Vehicle Type",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        ParkingSlotDAO dao = new ParkingSlotDAO();

        List<ParkingSlot> slots =
                dao.getAllSlots();

        for (ParkingSlot slot : slots) {

            Object[] row = {
                    slot.getSlotId(),
                    slot.getSlotNumber(),
                    slot.getVehicleType(),
                    slot.getStatus()
            };

            model.addRow(row);
        }

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
    }
}