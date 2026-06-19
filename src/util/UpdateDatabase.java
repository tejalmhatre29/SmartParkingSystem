package util;

import java.sql.Connection;
import java.sql.Statement;

public class UpdateDatabase {

    public static void main(String[] args) {

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement()
        ) {

            stmt.execute(
                "ALTER TABLE bookings ADD COLUMN amount INTEGER DEFAULT 0"
            );

            System.out.println(
                    "Amount column added successfully!"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}