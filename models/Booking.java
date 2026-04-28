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
        if (checkInDate != null && !checkInDate.isEmpty()) {
            this.checkInDate = checkInDate;
        } else {
            this.checkInDate = "2026-01-01";
        }
    }

    public void setCheckOutDate(String checkOutDate) {
        if (checkOutDate != null && !checkOutDate.isEmpty()) {
            this.checkOutDate = checkOutDate;
        } else {
            this.checkOutDate = "2026-01-02";
        }
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice >= 0) {
            this.totalPrice = totalPrice;
        } else {
            this.totalPrice = 0.0;
        }
    }

    public void setStatus(BookingStatus status) {
        if (status != null) {
            this.status = status;
        } else {
            this.status = BookingStatus.CANCELLED;
        }
    }
}
