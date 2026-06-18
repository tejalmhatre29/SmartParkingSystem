package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:parking.db";

    public static Connection getConnection() {

        try {

            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(URL);

            System.out.println("Database Connected Successfully!");

            return conn;

        } catch (ClassNotFoundException e) {

            System.out.println("SQLite Driver Not Found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return null;
    }
}