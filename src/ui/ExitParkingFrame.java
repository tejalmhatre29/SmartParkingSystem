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

public class ExitParkingFrame extends JFrame {

    private final Color PURPLE
            = new Color(155, 89, 182);

    private final Color LIGHT_PURPLE
            = new Color(240, 230, 255);

    public ExitParkingFrame() {

        setTitle("Exit Parking");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title
                = new JLabel("EXIT PARKING");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(PURPLE);

        title.setBounds(
                340,
                25,
                300,
                40
        );

        // Subtitle
        JLabel subtitle
                = new JLabel(
                        "Complete your active parking bookings"
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
                290,
                65,
                350,
                20
        );

        // White Card
        JPanel card
                = new JPanel(new BorderLayout());

        card.setBounds(
                30,
                110,
                880,
                370
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

        // Table Columns
        String[] columns = {
            "Booking ID",
            "Slot Number",
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

        // Center Align
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

        // Load Active Bookings
        BookingDAO bookingDAO
                = new BookingDAO();

        ParkingSlotDAO slotDAO
                = new ParkingSlotDAO();

        List<Booking> bookings
                = bookingDAO.getActiveBookings(
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
                booking.getStatus()
            };

            model.addRow(row);
        }

        JLabel countLabel
                = new JLabel(
                        "Active Bookings : "
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

        // Exit Button
        JButton exitBtn
                = new JButton(
                        "Exit Selected Booking"
                );

        exitBtn.setBounds(
                340,
                500,
                250,
                42
        );

        exitBtn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        exitBtn.setBackground(PURPLE);
        exitBtn.setForeground(Color.WHITE);

        exitBtn.setFocusPainted(false);
        exitBtn.setBorderPainted(false);

        exitBtn.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // Hover Effect
        exitBtn.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e
            ) {

                exitBtn.setBackground(
                        new Color(
                                175,
                                110,
                                200
                        )
                );
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e
            ) {

                exitBtn.setBackground(
                        PURPLE
                );
            }
        });

        // Exit Logic
        exitBtn.addActionListener(e -> {

            int selectedRow
                    = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a booking first!"
                );

                return;
            }

            int bookingId
                    = (int) model.getValueAt(
                            selectedRow,
                            0
                    );

            Booking selectedBooking
                    = bookings.get(selectedRow);

            int slotId
                    = selectedBooking.getSlotId();

            boolean completed
                    = bookingDAO.completeBookingById(
                            bookingId
                    );

            if (completed) {

                slotDAO.updateSlotStatus(
                        slotId,
                        "AVAILABLE"
                );

                UIManager.put(
                        "OptionPane.background",
                        Color.WHITE
                );

                UIManager.put(
                        "Panel.background",
                        Color.WHITE
                );

                UIManager.put(
                        "OptionPane.messageForeground",
                        new Color(155, 89, 182)
                );

                UIManager.put(
                        "Button.background",
                        new Color(155, 89, 182)
                );

                UIManager.put(
                        "Button.foreground",
                        Color.WHITE
                );

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                UIManager.put("OptionPane.messageForeground", new Color(155, 89, 182));
                UIManager.put("Button.background", new Color(155, 89, 182));
                UIManager.put("Button.foreground", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "✅ Parking Exit Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                model.removeRow(
                        selectedRow
                );

                bookings.remove(
                        selectedRow
                );

            } else {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Exit Failed!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        panel.add(title);
        panel.add(subtitle);
        panel.add(card);
        panel.add(exitBtn);

        add(panel);

        setVisible(true);
    }
}
