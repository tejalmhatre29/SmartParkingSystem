package ui;

import dao.BookingDAO;
import dao.ParkingSlotDAO;
import model.Booking;
import util.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ParkingHistoryFrame extends JFrame {

    public ParkingHistoryFrame() {

        setTitle("Parking History");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Booking ID",
                "Slot Number",
                "Booking Time",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        BookingDAO bookingDAO = new BookingDAO();
        ParkingSlotDAO slotDAO = new ParkingSlotDAO();

        List<Booking> bookings =
                bookingDAO.getBookingHistory(
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
                    booking.getBookingTime(),
                    booking.getStatus()
            };

            model.addRow(row);
        }

        add(new JScrollPane(table));

        setVisible(true);
    }
}