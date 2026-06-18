package ui;

import dao.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewUsersFrame extends JFrame {

    public ViewUsersFrame() {

        setTitle("Registered Users");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Name",
                "Email",
                "Vehicle Number",
                "Vehicle Type"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        UserDAO dao = new UserDAO();

        List<String[]> users =
                dao.getAllUsers();

        for (String[] user : users) {
            model.addRow(user);
        }

        add(new JScrollPane(table));

        setVisible(true);
    }
}