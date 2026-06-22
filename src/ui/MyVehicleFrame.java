package ui;

import dao.UserDAO;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import util.UserSession;

public class MyVehicleFrame extends JFrame {

    private final Color PURPLE
            = new Color(155, 89, 182);

    private final Color LIGHT_PURPLE
            = new Color(240, 230, 255);

    public MyVehicleFrame() {

        setTitle("My Vehicle");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(LIGHT_PURPLE);

        // Title
        JLabel title
                = new JLabel("MY VEHICLE DETAILS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(PURPLE);

        title.setBounds(
                180,
                30,
                350,
                40
        );

        // Subtitle
        JLabel subtitle
                = new JLabel(
                        "View your registered vehicle information"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(Color.GRAY);

        subtitle.setBounds(
                180,
                70,
                300,
                20
        );

        // White Card
        JPanel card = new JPanel(null);

        card.setBounds(
                75,
                120,
                480,
                220
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

        UserDAO dao = new UserDAO();

        String[] details
                = dao.getVehicleDetails(
                        UserSession.loggedInEmail
                );

        // Labels
        JLabel nameLabel
                = new JLabel("Name");

        JLabel emailLabel
                = new JLabel("Email");

        JLabel vehicleNoLabel
                = new JLabel("Vehicle Number");

        JLabel vehicleTypeLabel
                = new JLabel("Vehicle Type");

        JLabel[] labels = {
            nameLabel,
            emailLabel,
            vehicleNoLabel,
            vehicleTypeLabel
        };

        int y = 30;

        for (JLabel label : labels) {

            label.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            15
                    )
            );

            label.setForeground(PURPLE);

            label.setBounds(
                    40,
                    y,
                    140,
                    30
            );

            card.add(label);

            y += 45;
        }

        // Values
        JLabel nameValue
                = new JLabel(details[0]);

        JLabel emailValue
                = new JLabel(details[1]);

        JLabel vehicleNoValue
                = new JLabel(details[2]);

        JLabel vehicleTypeValue
                = new JLabel(details[3]);

        JLabel[] values = {
            nameValue,
            emailValue,
            vehicleNoValue,
            vehicleTypeValue
        };

        y = 30;

        for (JLabel value : values) {

            value.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            15
                    )
            );

            value.setBounds(
                    220,
                    y,
                    220,
                    30
            );

            card.add(value);

            y += 45;
        }

        panel.add(title);
        panel.add(subtitle);
        panel.add(card);

        add(panel);

        setVisible(true);
    }
}
