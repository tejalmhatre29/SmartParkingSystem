package ui;

import dao.BookingDAO;
import dao.ParkingSlotDAO;
import model.Booking;
import model.ParkingSlot;
import util.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookSlotFrame extends JFrame {

    public BookSlotFrame() {

        setTitle("Available Parking Slots");
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
        JButton bookBtn = new JButton("Book Selected Slot");

      

bookBtn.addActionListener(e -> {

    int selectedRow = table.getSelectedRow();

    if(selectedRow == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Select a slot first!"
        );

        return;
    }

    int slotId = (int) model.getValueAt(
            selectedRow,
            0
    );

    Booking booking = new Booking(
            0,
            UserSession.loggedInEmail,
            slotId,
            null,
            "ACTIVE"
    );

    BookingDAO bookingDAO =
            new BookingDAO();

    boolean bookingAdded =
            bookingDAO.addBooking(booking);

    if(bookingAdded) {

        ParkingSlotDAO slotDAO =
                new ParkingSlotDAO();

        slotDAO.updateSlotStatus(
                slotId,
                "OCCUPIED"
        );

        JOptionPane.showMessageDialog(
                this,
                "Booking Successful!"
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Booking Failed!"
        );
    }
});

        ParkingSlotDAO dao = new ParkingSlotDAO();

        List<ParkingSlot> slots =
                dao.getAvailableSlots();

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

        setLayout(new java.awt.BorderLayout());

add(scrollPane, java.awt.BorderLayout.CENTER);
add(bookBtn, java.awt.BorderLayout.SOUTH);

        setVisible(true);
    }
}