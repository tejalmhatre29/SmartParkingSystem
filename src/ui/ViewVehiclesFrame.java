package ui;

import dao.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewVehiclesFrame extends JFrame {

    public ViewVehiclesFrame() {

        setTitle("View Vehicles");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Owner Name",
                "Vehicle Number",
                "Vehicle Type"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        UserDAO dao = new UserDAO();

        List<String[]> vehicles =
                dao.getAllVehicles();

        for (String[] vehicle : vehicles) {
            model.addRow(vehicle);
        }

        add(new JScrollPane(table));

        setVisible(true);
    }
}