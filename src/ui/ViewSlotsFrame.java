package ui;

import dao.ParkingSlotDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ParkingSlot;

public class ViewSlotsFrame extends JFrame {

    public ViewSlotsFrame() {

        setTitle("View Parking Slots");
        setSize(800, 500);
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

        JButton deleteBtn =
                new JButton("Delete Selected Slot");

        deleteBtn.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a slot first!"
                );

                return;
            }

            int slotId =
                    (int) model.getValueAt(
                            selectedRow,
                            0
                    );

            String status =
                    model.getValueAt(
                            selectedRow,
                            3
                    ).toString();

            if(status.equals("OCCUPIED")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Occupied slots cannot be deleted!"
                );

                return;
            }

            boolean deleted =
                    dao.deleteSlot(slotId);

            if(deleted) {

                model.removeRow(selectedRow);

                JOptionPane.showMessageDialog(
                        this,
                        "Slot Deleted Successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Delete Failed!"
                );
            }
        });

        setLayout(new BorderLayout());

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        add(deleteBtn,
                BorderLayout.SOUTH);

        setVisible(true);
    }
}