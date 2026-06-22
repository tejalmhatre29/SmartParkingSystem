package ui;

import dao.BookingDAO;
import dao.ParkingSlotDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Booking;
import util.UserSession;

public class ParkingHistoryFrame extends JFrame {

    private final Color PURPLE
            = new Color(155, 89, 182);

    private final Color LIGHT_PURPLE
            = new Color(240, 230, 255);

    public ParkingHistoryFrame() {

        setTitle("Parking History");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title
                = new JLabel("PARKING HISTORY");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(PURPLE);

        title.setBounds(
                300,
                25,
                350,
                40
        );

        // Subtitle
        JLabel subtitle
                = new JLabel(
                        "View all your parking bookings"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(Color.GRAY);

        subtitle.setBounds(
                320,
                65,
                300,
                20
        );

        // White Card
        JPanel card
                = new JPanel(new BorderLayout());

        card.setBounds(
                30,
                110,
                880,
                400
        );

        card.setBackground(Color.WHITE);

        card.setBorder(
                new LineBorder(
                        new Color(
                                220,
                                220,
                                220
                        ),
                        1,
                        true
                )
        );

        // Columns
        String[] columns = {
            "Booking ID",
            "Slot Number",
            "Booking Time",
            "Status"
        };

        DefaultTableModel model
                = new DefaultTableModel(
                        columns,
                        0
                ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        JTable table
                = new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        table.setSelectionBackground(
                new Color(
                        230,
                        210,
                        255
                )
        );

        table.setGridColor(
                new Color(
                        230,
                        230,
                        230
                )
        );

        // Center Align Table Data
        DefaultTableCellRenderer center
                = new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
                i < table.getColumnCount();
                i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }

        // Header Style
        JTableHeader header
                = table.getTableHeader();

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        header.setBackground(PURPLE);
        header.setForeground(Color.WHITE);

        // Load Data
        BookingDAO bookingDAO
                = new BookingDAO();

        ParkingSlotDAO slotDAO
                = new ParkingSlotDAO();

        List<Booking> bookings
                = bookingDAO.getBookingHistory(
                        UserSession.loggedInEmail
                );

        for (Booking booking : bookings) {

            String slotNumber
                    = slotDAO.getSlotNumber(
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

        // Count Label
        JLabel countLabel
                = new JLabel(
                        "Total Records : "
                        + bookings.size()
                );

        countLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        countLabel.setForeground(PURPLE);

        JPanel topPanel
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        topPanel.setBackground(
                Color.WHITE
        );

        topPanel.add(countLabel);

        JScrollPane scrollPane
                = new JScrollPane(table);

        scrollPane.setBorder(null);

        card.add(
                topPanel,
                BorderLayout.NORTH
        );

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        panel.add(title);
        panel.add(subtitle);
        panel.add(card);

        add(panel);

        setVisible(true);
    }
}
