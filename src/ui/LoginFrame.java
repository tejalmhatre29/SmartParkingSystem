package ui;

import dao.UserDAO;
import java.awt.*;
import javax.swing.*;
import util.UserSession;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Smart Parking Management System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        // Title
        JLabel title = new JLabel("SMART PARKING MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(120, 30, 400, 30);

        // Role
        JLabel roleLabel = new JLabel("Role");
        roleLabel.setBounds(120, 100, 100, 25);

        String[] roles = {"Admin", "Vehicle Owner"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setBounds(250, 100, 180, 30);

        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(120, 150, 100, 25);

        JTextField emailField = new JTextField();
        emailField.setBounds(250, 150, 180, 30);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(120, 200, 100, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 200, 180, 30);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(170, 280, 120, 40);

        loginBtn.addActionListener(e -> {

            String email = emailField.getText().trim();

            String password
                    = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Email and Password!"
                );

                return;
            }

            UserDAO userDAO = new UserDAO();

            boolean valid
                    = userDAO.validateLogin(email, password);

            if (valid) {

                UserSession.loggedInEmail = email;

                String role
                        = userDAO.getUserRole(email, password);

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                );

                if (role.equals("ADMIN")) {

                    new AdminDashboard();

                } else {

                    new OwnerDashboard();

                }

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Credentials!"
                );
            }
        });

        // Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(310, 280, 120, 40);

        registerBtn.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });

        panel.add(title);
        panel.add(roleLabel);
        panel.add(roleBox);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(registerBtn);

        add(panel);

        setVisible(true);
    }
}
