public class Booking {
    int bookingId;
    User userId;         
    // int accommodationId;  
    String checkInDate;
    String checkOutDate;
    double totalPrice;
    BookingStatus status;   

    // CONSTRUCTOR
    public Booking(int bookingId, User userId, String checkInDate, String checkOutDate, double totalPrice, BookingStatus status) {
        this.bookingId = bookingId;
        this.userId = userId;
        // this.accommodationId = accommodationId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // GETTERS
    public int getBookingId() {
        return bookingId;
    }

    public User getUserId() {
        return userId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    // SETTERS
    public void setCheckInDate(String checkInDate) {
        if (checkInDate != null && !checkInDate.isEmpty()) {
            this.checkInDate = checkInDate;
        }
    }

    public void setCheckOutDate(String checkOutDate) {
        if (checkOutDate != null && !checkOutDate.isEmpty()) {
            this.checkOutDate = checkOutDate;
        }
    }

    public void setTotalPrice(double totalPrice) {

        if (totalPrice >= 0) {
            this.totalPrice = totalPrice;
        }
    }

    public void setStatus(BookingStatus status) {
        if (status != null) {
            this.status = status;
        }
    }



}
