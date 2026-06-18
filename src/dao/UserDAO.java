package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class UserDAO {

    public boolean insertUser(
            String name,
            String email,
            String password,
            String role,
            String vehicleNumber,
            String vehicleType) {

        String query = """
                INSERT INTO users
                (name, email, password, role, vehicle_number, vehicle_type)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)
        ) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, role);
            pstmt.setString(5, vehicleNumber);
            pstmt.setString(6, vehicleType);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ADD THIS METHOD HERE
    public boolean validateLogin(String email, String password) {

        String query =
                "SELECT * FROM users WHERE email=? AND password=?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)
        ) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            return pstmt.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getUserRole(String email, String password) {

    String query =
            "SELECT role FROM users WHERE email=? AND password=?";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query)
    ) {

        pstmt.setString(1, email);
        pstmt.setString(2, password);

        var rs = pstmt.executeQuery();

        if(rs.next()) {
            return rs.getString("role");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

public List<String[]> getAllUsers() {

    List<String[]> users = new ArrayList<>();

    String query = """
            SELECT name,
                   email,
                   vehicle_number,
                   vehicle_type
            FROM users
            WHERE role='OWNER'
            """;

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query)
    ) {

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            String[] user = {
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("vehicle_number"),
                    rs.getString("vehicle_type")
            };

            users.add(user);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return users;
}

public String[] getVehicleDetails(String email) {

    String query = """
            SELECT name,
                   email,
                   vehicle_number,
                   vehicle_type
            FROM users
            WHERE email = ?
            """;

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query)
    ) {

        pstmt.setString(1, email);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            return new String[] {
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("vehicle_number"),
                    rs.getString("vehicle_type")
            };
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
}