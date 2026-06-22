package ui;

import dao.UserDAO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import util.UserSession;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Smart Parking Management System");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Theme Colors
        Color PURPLE = new Color(155, 89, 182);
        Color LIGHT_PURPLE = new Color(240, 230, 255);

        // Main Panel
        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Moving Car Animation
        JLabel car = new JLabel("🚗");
        car.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        car.setBounds(-80, 20, 60, 60);
        panel.add(car);

        Timer carTimer = new Timer(10, null);

        carTimer.addActionListener(e -> {

            car.setLocation(
                    car.getX() + 2,
                    car.getY()
            );

            if (car.getX() > 700) {
                carTimer.stop();
            }
        });

        carTimer.start();

        // Title
        JLabel title = new JLabel("SMART PARKING MANAGEMENT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(PURPLE);
        title.setBounds(140, 70, 450, 35);
        panel.add(title);

        // Login Card
        JPanel card = new JPanel(null);
        card.setBounds(-400, 130, 350, 260); // Starts outside screen
        card.setBackground(Color.WHITE);
        card.setBorder(
                new LineBorder(
                        new Color(220, 220, 220),
                        1,
                        true
                )
        );

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailLabel.setBounds(30, 35, 80, 25);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 30, 190, 35);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setBounds(30, 95, 80, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 90, 190, 35);

        // Show Password Checkbox
        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBackground(Color.WHITE);
        showPassword.setBounds(120, 130, 150, 25);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(40, 190, 120, 40);
        loginBtn.setBackground(PURPLE);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setFocusPainted(false);

        // Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(190, 190, 120, 40);
        registerBtn.setBackground(Color.WHITE);
        registerBtn.setForeground(PURPLE);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(
                BorderFactory.createLineBorder(PURPLE)
        );

        // Login Hover Effect
        loginBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(
                        new Color(175, 110, 200)
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(PURPLE);
            }
        });

        // Register Hover Effect
        registerBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                registerBtn.setBackground(LIGHT_PURPLE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerBtn.setBackground(Color.WHITE);
            }
        });

        // Login Action
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

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                UIManager.put("OptionPane.messageForeground", new Color(155, 89, 182));
                UIManager.put("Button.background", new Color(155, 89, 182));
                UIManager.put("Button.foreground", Color.WHITE);

                JOptionPane.showMessageDialog(
                        this,
                        "✅ Login Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (role.equals("ADMIN")) {

                    new AdminDashboard();

                } else {

                    new OwnerDashboard();
                }

                dispose();

            } else {

                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE);
                UIManager.put("OptionPane.messageForeground", Color.RED);

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Invalid Credentials!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Register Action
        registerBtn.addActionListener(e -> {

            new RegisterFrame();
            dispose();

        });

        // Add Components
        card.add(emailLabel);
        card.add(emailField);
        card.add(passwordLabel);
        card.add(passwordField);
        card.add(showPassword);
        card.add(loginBtn);
        card.add(registerBtn);

        panel.add(card);

        // Slide Animation for Login Card
        Timer slideTimer = new Timer(5, null);

        slideTimer.addActionListener(e -> {

            int x = card.getX();

            if (x < 170) {

                card.setLocation(
                        x + 5,
                        card.getY()
                );

            } else {

                slideTimer.stop();
            }
        });

        slideTimer.start();

        add(panel);
        setVisible(true);
    }
}
