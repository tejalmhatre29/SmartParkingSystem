package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ParkingSlot;
import util.DBConnection;

public class ParkingSlotDAO {

    public boolean addSlot(ParkingSlot slot) {

        String query = """
                INSERT INTO parking_slots
                (slot_number, vehicle_type, status)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt =
                        conn.prepareStatement(query)
        ) {

            pstmt.setString(1, slot.getSlotNumber());
            pstmt.setString(2, slot.getVehicleType());
            pstmt.setString(3, slot.getStatus());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<ParkingSlot> getAllSlots() {

    List<ParkingSlot> slots = new ArrayList<>();

    String query = "SELECT * FROM parking_slots";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
    ) {

        while(rs.next()) {

            ParkingSlot slot =
                    new ParkingSlot(
                            rs.getInt("slot_id"),
                            rs.getString("slot_number"),
                            rs.getString("vehicle_type"),
                            rs.getString("status")
                    );

            slots.add(slot);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return slots;
}

public List<ParkingSlot> getAvailableSlots() {

    List<ParkingSlot> slots = new ArrayList<>();

    String query =
            "SELECT * FROM parking_slots WHERE status='AVAILABLE'";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
    ) {

        while(rs.next()) {

            ParkingSlot slot =
                    new ParkingSlot(
                            rs.getInt("slot_id"),
                            rs.getString("slot_number"),
                            rs.getString("vehicle_type"),
                            rs.getString("status")
                    );

            slots.add(slot);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return slots;
}

public boolean updateSlotStatus(int slotId, String status) {

    String query =
            "UPDATE parking_slots SET status=? WHERE slot_id=?";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query)
    ) {

        pstmt.setString(1, status);
        pstmt.setInt(2, slotId);

        return pstmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

public String getSlotNumber(int slotId) {

    String query =
            "SELECT slot_number FROM parking_slots WHERE slot_id=?";

    try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(query)
    ) {

        pstmt.setInt(1, slotId);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            return rs.getString("slot_number");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "";
}
}