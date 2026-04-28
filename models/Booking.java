package models;

public class Booking {
    private int bookingId;
    private User userId;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;
    private BookingStatus status;

    public Booking(int bookingId, User userId, String checkInDate, String checkOutDate, double totalPrice, BookingStatus status) {
        this.bookingId = bookingId;
        this.userId = userId;
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        setTotalPrice(totalPrice);
        setStatus(status);
    }

    public int getBookingId() { return bookingId; }
    public User getUserId() { return userId; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public double getTotalPrice() { return totalPrice; }
    public BookingStatus getStatus() { return status; }

    public void setCheckInDate(String checkInDate) {
        if (checkInDate == null || checkInDate.isEmpty()) {
            System.out.println("Check-in date cannot be empty. Using default: 2026-01-01");
            this.checkInDate = "2026-01-01";
        } else {
            this.checkInDate = checkInDate;
        }
    }

    public void setCheckOutDate(String checkOutDate) {
        if (checkOutDate == null || checkOutDate.isEmpty()) {
            System.out.println("Check-out date cannot be empty. Using default: 2026-01-02");
            this.checkOutDate = "2026-01-02";
        } else {
            this.checkOutDate = checkOutDate;
        }
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice < 0) {
            System.out.println("Total price cannot be negative. Setting to 0.");
            this.totalPrice = 0.0;
        } else {
            this.totalPrice = totalPrice;
        }
    }

    public void setStatus(BookingStatus status) {
        if (status == null) {
            System.out.println("Status cannot be null. Setting to CANCELLED.");
            this.status = BookingStatus.CANCELLED;
        } else {
            this.status = status;
        }
    }
}