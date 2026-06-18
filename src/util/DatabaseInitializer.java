package util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL,
                role TEXT NOT NULL,
                vehicle_number TEXT,
                vehicle_type TEXT
            );
        """;

        String createSlotsTable = """
    CREATE TABLE IF NOT EXISTS parking_slots (
        slot_id INTEGER PRIMARY KEY AUTOINCREMENT,
        slot_number TEXT UNIQUE NOT NULL,
        vehicle_type TEXT NOT NULL,
        status TEXT NOT NULL
    );
""";

       String createBookingsTable = """
    CREATE TABLE IF NOT EXISTS bookings (
        booking_id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_email TEXT NOT NULL,
        slot_id INTEGER NOT NULL,
        booking_time DATETIME DEFAULT CURRENT_TIMESTAMP,
        status TEXT NOT NULL
    );
""";

        try (
                Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {

            stmt.execute(createUsersTable);
stmt.execute(createSlotsTable);
stmt.execute(createBookingsTable);

            String insertAdmin = """
    INSERT OR IGNORE INTO users
    (name, email, password, role)
    VALUES
    ('Admin', 'admin@gmail.com', 'Admin@123', 'ADMIN');
""";

            stmt.execute(insertAdmin);

            System.out.println("Users table created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
