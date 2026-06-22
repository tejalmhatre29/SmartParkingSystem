package ui;

import dao.BookingDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.RevenueReport;

public class RevenueReportFrame extends JFrame {

    private final Color PURPLE
            = new Color(155, 89, 182);

    private final Color LIGHT_PURPLE
            = new Color(240, 230, 255);

    public RevenueReportFrame() {

        setTitle("Revenue Report");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title
                = new JLabel("REVENUE REPORT");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(PURPLE);
        title.setBounds(310, 25, 350, 40);

        // Subtitle
        JLabel subtitle
                = new JLabel(
                        "Track parking revenue and completed bookings"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(260, 65, 400, 20);

        // White Card
        JPanel card
                = new JPanel(new BorderLayout());

        card.setBounds(
                30,
                150,
                880,
                350
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
            "Date",
            "Revenue (₹)",
            "Completed Bookings"
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

        // Table Styling
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

        // Center Alignment
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
        BookingDAO dao
                = new BookingDAO();

        List<RevenueReport> reports
                = dao.getRevenueReports();

        double totalRevenue = 0;
        int totalBookings = 0;

        for (RevenueReport report : reports) {

            Object[] row = {
                report.getDate(),
                report.getTotalRevenue(),
                report.getTotalBookings()
            };

            model.addRow(row);

            totalRevenue
                    += report.getTotalRevenue();

            totalBookings
                    += report.getTotalBookings();
        }

        // Summary Cards
        JPanel summaryPanel
                = new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        summaryPanel.setBounds(
                150,
                95,
                650,
                40
        );

        summaryPanel.setBackground(
                LIGHT_PURPLE
        );

        JLabel revenueLabel
                = new JLabel(
                        "Total Revenue : ₹"
                        + String.format(
                                "%.2f",
                                totalRevenue
                        ),
                        SwingConstants.CENTER
                );

        revenueLabel.setOpaque(true);
        revenueLabel.setBackground(Color.WHITE);
        revenueLabel.setForeground(PURPLE);

        revenueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        revenueLabel.setBorder(
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

        JLabel bookingsLabel
                = new JLabel(
                        "Completed Bookings : "
                        + totalBookings,
                        SwingConstants.CENTER
                );

        bookingsLabel.setOpaque(true);
        bookingsLabel.setBackground(Color.WHITE);
        bookingsLabel.setForeground(PURPLE);

        bookingsLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        bookingsLabel.setBorder(
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

        summaryPanel.add(revenueLabel);
        summaryPanel.add(bookingsLabel);

        JScrollPane scrollPane
                = new JScrollPane(table);

        scrollPane.setBorder(null);

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        panel.add(title);
        panel.add(subtitle);
        panel.add(summaryPanel);
        panel.add(card);

        add(panel);

        setVisible(true);
    }
}
