package ui;

import dao.BookingDAO;
import model.RevenueReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RevenueReportFrame extends JFrame {

    public RevenueReportFrame() {

        setTitle("Revenue Report");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Date",
                "Revenue (₹)",
                "Completed Bookings"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        BookingDAO dao = new BookingDAO();

        List<RevenueReport> reports =
                dao.getRevenueReports();

        for (RevenueReport report : reports) {

            Object[] row = {
                    report.getDate(),
                    report.getTotalRevenue(),
                    report.getTotalBookings()
            };

            model.addRow(row);
        }

        add(new JScrollPane(table));

        setVisible(true);
    }
}