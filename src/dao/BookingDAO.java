package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import util.DBConnection;

public class BookingDAO {

    public boolean addBooking(Booking booking) {

        String query = """
                INSERT INTO bookings
                (user_email, slot_id, status)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement pstmt
                = conn.prepareStatement(query)) {

            pstmt.setString(1, booking.getUserEmail());
            pstmt.setInt(2, booking.getSlotId());
            pstmt.setString(3, booking.getStatus());

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getActiveSlotId(String email) {

        String query = """
            SELECT slot_id
            FROM bookings
            WHERE user_email = ?
            AND status = 'ACTIVE'
            ORDER BY booking_id DESC
            LIMIT 1
            """;

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement pstmt
                = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("slot_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean completeBooking(String email) {

        String query = """
            UPDATE bookings
            SET status = 'COMPLETED'
            WHERE user_email = ?
            AND status = 'ACTIVE'
            """;

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement pstmt
                = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Booking> getActiveBookings(String email) {

        List<Booking> bookings = new ArrayList<>();

        String query = """
            SELECT *
            FROM bookings
            WHERE user_email = ?
            AND status = 'ACTIVE'
            """;

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement pstmt
                = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getString("user_email"),
                        rs.getInt("slot_id"),
                        rs.getString("booking_time"),
                        rs.getString("status")
                );

                bookings.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public boolean completeBookingById(int bookingId) {

        String query
                = "UPDATE bookings SET status='COMPLETED' WHERE booking_id=?";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement pstmt
                = conn.prepareStatement(query)) {

            pstmt.setInt(1, bookingId);

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Booking> getBookingHistory(String email) {

        List<Booking> bookings = new ArrayList<>();

        String query = """
            SELECT *
            FROM bookings
            WHERE user_email = ?
            ORDER BY booking_id DESC
            """;

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement pstmt
                = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getString("user_email"),
                        rs.getInt("slot_id"),
                        rs.getString("booking_time"),
                        rs.getString("status")
                );

                bookings.add(booking);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }
}
