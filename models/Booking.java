package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {

    private int bookingId;
    private User user;
    private Accommodation accommodation;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private BookingStatus status;
    private String confirmedDate;

    public Booking(int bookingId, User user, Accommodation accommodation,
            String checkInDate, String checkOutDate, BookingStatus status) {
        this.bookingId = bookingId;
        this.user = user;
        this.accommodation = accommodation;
        this.confirmedDate = java.time.LocalDate.now().toString();

<<<<<<< HEAD
        setCheckInDate(checkInDate); // to save from invalid dates  
        setCheckOutDate(checkOutDate);
=======
        this.checkInDate = LocalDate.parse(checkInDate);
        this.checkOutDate = LocalDate.parse(checkOutDate);

>>>>>>> 3a96ac6dc0646f42c67ec27c2d1368d20f754daf
        setStatus(status);
        this.totalPrice = calculateTotalPrice();
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public String getCheckInDate() {
        return checkInDate.toString();
    }

    public String getCheckOutDate() {
        return checkOutDate.toString();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public String getConfirmedDate() {
        return confirmedDate;
    }

    public User getUserId() {
        return user;
    }

    // Setters
    public void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate == null) {
            System.out.println("  [WARNING] Check-in date empty. Using default 2026-01-01.");
            this.checkInDate = LocalDate.parse("2026-01-01");
        } else {
            this.checkInDate = checkInDate;
        }
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        if (checkOutDate == null) {
            System.out.println("  [WARNING] Check-out date empty. Using default 2026-01-02.");
            this.checkOutDate = LocalDate.parse("2026-01-02");
        } else {
            this.checkOutDate = checkOutDate;
        }
    }

    public void setStatus(BookingStatus status) {
        if (status == null) {
            System.out.println("  [WARNING] Status cannot be null. Setting to CANCELLED.");
            this.status = BookingStatus.CANCELLED;
        } else {
            this.status = status;
        }
    }

    // Status flow validation 
    public boolean canChangeTo(BookingStatus newStatus) {
        if (this.status == BookingStatus.CONFIRMED) {
            if (newStatus == BookingStatus.CHECKED_IN || newStatus == BookingStatus.CANCELLED) {
                return true;
            } else {
                return false;
            }
        } else if (this.status == BookingStatus.CHECKED_IN) {
            if (newStatus == BookingStatus.CHECKED_OUT || newStatus == BookingStatus.CANCELLED) {
                return true;
            } else {
                return false;
            }
        } else if (this.status == BookingStatus.CANCELLED) {
            if (newStatus == BookingStatus.REFUNDED) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Business Logic 
    public int getNumberOfNights() {
        if (checkInDate == null || checkOutDate == null) return 0;
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    private double calculateTotalPrice() {
        if (accommodation == null) return 0.0;
        return accommodation.getPricePerNight() * getNumberOfNights();
    }
}