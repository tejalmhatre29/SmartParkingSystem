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
import model.ParkingSlot;
import util.UserSession;

public class BookSlotFrame extends JFrame {

    private final Color PURPLE
            = new Color(155, 89, 182);

    private final Color LIGHT_PURPLE
            = new Color(240, 230, 255);

    public BookSlotFrame() {

        setTitle("Book Parking Slot");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title
                = new JLabel("AVAILABLE PARKING SLOTS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(PURPLE);
        title.setBounds(
                240,
                25,
                500,
                40
        );

        // Subtitle
        JLabel subtitle
                = new JLabel(
                        "Select a parking slot and complete payment"
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
                275,
                65,
                400,
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
            "ID",
            "Slot Number",
            "Vehicle Type",
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

        // Load Available Slots
        ParkingSlotDAO dao
                = new ParkingSlotDAO();

        List<ParkingSlot> slots
                = dao.getAvailableSlots();

        for (ParkingSlot slot : slots) {

            Object[] row = {
                slot.getSlotId(),
                slot.getSlotNumber(),
                slot.getVehicleType(),
                slot.getStatus()
            };

            model.addRow(row);
        }

        JLabel countLabel
                = new JLabel(
                        "Available Slots : "
                        + slots.size()
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

        // Book Button
        JButton bookBtn
                = new JButton(
                        "Book Selected Slot"
                );

        bookBtn.setBounds(
                350,
                500,
                230,
                42
        );

        bookBtn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        bookBtn.setBackground(PURPLE);
        bookBtn.setForeground(Color.WHITE);

        bookBtn.setFocusPainted(false);
        bookBtn.setBorderPainted(false);

        bookBtn.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        bookBtn.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e
            ) {

                bookBtn.setBackground(
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

                bookBtn.setBackground(
                        PURPLE
                );
            }
        });

        // Booking Logic
        bookBtn.addActionListener(e -> {

            int selectedRow
                    = table.getSelectedRow();

            if (selectedRow == -1) {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "⚠️ Select a slot first!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int slotId
                    = (int) model.getValueAt(
                            selectedRow,
                            0
                    );

            String vehicleType
                    = model.getValueAt(
                            selectedRow,
                            2
                    ).toString();

            int fee = 0;

            switch (vehicleType.toUpperCase()) {

                case "BIKE":
                    fee = 40;
                    break;

                case "CAR":
                    fee = 60;
                    break;

                case "BUS":
                    fee = 80;
                    break;

                case "TRUCK":
                    fee = 90;
                    break;
            }

            int choice
                    = JOptionPane.showConfirmDialog(
                            this,
                            "Parking Fee : ₹" + fee
                            + "\n\nProceed to Payment?",
                            "Payment",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice != JOptionPane.YES_OPTION) {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "⚠️ Payment Cancelled!",
                        "Cancelled",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("Panel.background", Color.WHITE);

            JOptionPane.showMessageDialog(
                    this,
                    "💳 Payment Successful!\n₹" + fee + " received.",
                    "Payment",
                    JOptionPane.INFORMATION_MESSAGE
            );

            Booking booking
                    = new Booking(
                            0,
                            UserSession.loggedInEmail,
                            slotId,
                            null,
                            "ACTIVE",
                            fee
                    );

            BookingDAO bookingDAO
                    = new BookingDAO();

            boolean bookingAdded
                    = bookingDAO.addBooking(
                            booking
                    );

            if (bookingAdded) {

                ParkingSlotDAO slotDAO
                        = new ParkingSlotDAO();

                slotDAO.updateSlotStatus(
                        slotId,
                        "OCCUPIED"
                );

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "🚗 Booking Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                model.removeRow(
                        selectedRow
                );

            } else {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Booking Failed!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        panel.add(title);
        panel.add(subtitle);
        panel.add(card);
        panel.add(bookBtn);

        add(panel);

        setVisible(true);
    }
}
