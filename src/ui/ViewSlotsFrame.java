package ui;

import dao.ParkingSlotDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.ParkingSlot;

public class ViewSlotsFrame extends JFrame {

    private final Color PURPLE
            = new Color(155, 89, 182);

    private final Color LIGHT_PURPLE
            = new Color(240, 230, 255);

    public ViewSlotsFrame() {

        setTitle("View Parking Slots");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title
                = new JLabel("PARKING SLOTS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(PURPLE);
        title.setBounds(330, 25, 300, 40);

        // Subtitle
        JLabel subtitle
                = new JLabel(
                        "Manage and monitor parking slots"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(315, 65, 300, 20);

        // White Card
        JPanel card
                = new JPanel(new BorderLayout());

        card.setBounds(
                30,
                110,
                880,
                380
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

        // Header Styling
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
        ParkingSlotDAO dao
                = new ParkingSlotDAO();

        List<ParkingSlot> slots
                = dao.getAllSlots();

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
                        "Total Slots : "
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

        // Delete Button
        JButton deleteBtn
                = new JButton(
                        "Delete Selected Slot"
                );

        deleteBtn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        deleteBtn.setBackground(PURPLE);
        deleteBtn.setForeground(Color.WHITE);

        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorderPainted(false);

        deleteBtn.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        deleteBtn.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                deleteBtn.setBackground(
                        new Color(
                                175,
                                110,
                                200
                        )
                );
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                deleteBtn.setBackground(
                        PURPLE
                );
            }
        });

        deleteBtn.addActionListener(e -> {

            int selectedRow
                    = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a slot first!"
                );

                return;
            }

            int slotId
                    = (int) model.getValueAt(
                            selectedRow,
                            0
                    );

            String status
                    = model.getValueAt(
                            selectedRow,
                            3
                    ).toString();

            if (status.equals("OCCUPIED")) {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "⚠️ Occupied slots cannot be deleted!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            boolean deleted
                    = dao.deleteSlot(slotId);

            if (deleted) {

                model.removeRow(
                        selectedRow
                );

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "🗑️ Slot Deleted Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Delete Failed!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        deleteBtn.setBounds(
                360,
                510,
                220,
                40
        );

        panel.add(title);
        panel.add(subtitle);
        panel.add(card);
        panel.add(deleteBtn);

        add(panel);

        setVisible(true);
    }
}
