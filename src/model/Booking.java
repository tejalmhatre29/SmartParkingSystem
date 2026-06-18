package model;

public class Booking {

    private int bookingId;
    private String userEmail;
    private int slotId;
    private String bookingTime;
    private String status;

    public Booking() {
    }

    public Booking(
            int bookingId,
            String userEmail,
            int slotId,
            String bookingTime,
            String status) {

        this.bookingId = bookingId;
        this.userEmail = userEmail;
        this.slotId = slotId;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}