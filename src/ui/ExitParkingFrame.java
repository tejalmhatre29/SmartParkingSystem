package ui;

import dao.BookingDAO;
import dao.ParkingSlotDAO;
import model.Booking;
import util.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ExitParkingFrame extends JFrame {

    public ExitParkingFrame() {

        setTitle("Exit Parking");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Booking ID",
                "Slot Number",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        BookingDAO bookingDAO = new BookingDAO();
        ParkingSlotDAO slotDAO = new ParkingSlotDAO();

        List<Booking> bookings =
                bookingDAO.getActiveBookings(
                        UserSession.loggedInEmail
                );

        for (Booking booking : bookings) {

            String slotNumber =
                    slotDAO.getSlotNumber(
                            booking.getSlotId()
                    );

            Object[] row = {
                    booking.getBookingId(),
                    slotNumber,
                    booking.getStatus()
            };

            model.addRow(row);
        }

        JButton exitBtn =
                new JButton("Exit Selected Booking");

        exitBtn.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a booking first!"
                );

                return;
            }

            int bookingId =
                    (int) model.getValueAt(
                            selectedRow,
                            0
                    );

            Booking selectedBooking =
                    bookings.get(selectedRow);

            int slotId =
                    selectedBooking.getSlotId();

            boolean completed =
                    bookingDAO.completeBookingById(
                            bookingId
                    );

            if (completed) {

                slotDAO.updateSlotStatus(
                        slotId,
                        "AVAILABLE"
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Parking Exit Successful!"
                );

                model.removeRow(selectedRow);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Exit Failed!"
                );
            }
        });

        setLayout(new java.awt.BorderLayout());

        add(new JScrollPane(table),
                java.awt.BorderLayout.CENTER);

        add(exitBtn,
                java.awt.BorderLayout.SOUTH);

        setVisible(true);
    }
}