package model;

public class RevenueReport {

    private String date;
    private int totalRevenue;
    private int totalBookings;

    public RevenueReport(
            String date,
            int totalRevenue,
            int totalBookings) {

        this.date = date;
        this.totalRevenue = totalRevenue;
        this.totalBookings = totalBookings;
    }

    public String getDate() {
        return date;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public int getTotalBookings() {
        return totalBookings;
    }
}