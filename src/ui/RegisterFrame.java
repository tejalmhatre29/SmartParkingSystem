package ui;

import dao.UserDAO;
import java.awt.*;
import javax.swing.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {

        setTitle("Vehicle Owner Registration");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("VEHICLE OWNER REGISTRATION");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(120, 20, 400, 30);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(100, 80, 120, 25);

        JTextField nameField = new JTextField();
        nameField.setBounds(250, 80, 220, 30);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(100, 130, 120, 25);

        JTextField emailField = new JTextField();
        emailField.setBounds(250, 130, 220, 30);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 180, 120, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 180, 220, 30);

        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(100, 230, 120, 25);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(250, 230, 220, 30);

        JLabel vehicleNoLabel = new JLabel("Vehicle Number");
        vehicleNoLabel.setBounds(100, 280, 120, 25);

        JTextField vehicleNoField = new JTextField();
        vehicleNoField.setBounds(250, 280, 220, 30);

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type");
        vehicleTypeLabel.setBounds(100, 330, 120, 25);

        String[] types = {"Car", "Bike", "Truck", "Bus"};
        JComboBox<String> vehicleTypeBox = new JComboBox<>(types);
        vehicleTypeBox.setBounds(250, 330, 220, 30);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(180, 400, 120, 40);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(330, 400, 120, 40);

// Register Validation
        registerBtn.addActionListener(e -> {

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());

            String vehicleNumber = vehicleNoField.getText().trim();

            if (name.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || confirmPassword.isEmpty()
                    || vehicleNumber.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "All fields are required!");
                return;
            }

            String emailPattern
                    = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            if (!email.matches(emailPattern)) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a valid email address!");
                return;
            }

            String passwordPattern
                    = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

            if (!password.matches(passwordPattern)) {

                JOptionPane.showMessageDialog(this,
                        """
                Password must contain:
                - At least 8 characters
                - 1 uppercase letter
                - 1 digit
                - 1 special character
                """);
                return;
            }

            if (!password.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(this,
                        "Passwords do not match!");
                return;
            }

            UserDAO userDAO = new UserDAO();

            boolean inserted = userDAO.insertUser(
                    name,
                    email,
                    password,
                    "OWNER",
                    vehicleNumber,
                    vehicleTypeBox.getSelectedItem().toString()
            );

            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("Panel.background", Color.WHITE);
            UIManager.put("OptionPane.messageForeground", new Color(155, 89, 182));

            JOptionPane.showMessageDialog(
                    this,
                    "🎉 Registration Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("Panel.background", Color.WHITE);

            JOptionPane.showMessageDialog(
                    this,
                    "❌ Registration Failed!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        });

        backBtn.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        panel.add(title);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmLabel);
        panel.add(confirmField);
        panel.add(vehicleNoLabel);
        panel.add(vehicleNoField);
        panel.add(vehicleTypeLabel);
        panel.add(vehicleTypeBox);
        panel.add(registerBtn);
        panel.add(backBtn);

        add(panel);

        setVisible(true);
    }
}
