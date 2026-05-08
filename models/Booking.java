package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import interfaces.StatusChangeable;
import interfaces.Displayable;

public class Booking implements StatusChangeable, Displayable {

    private int bookingId;
    private User user;
    private Accommodation accommodation;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private BookingStatus status;
    private String confirmedDate;  // This is your booking date

    public Booking(int bookingId, User user, Accommodation accommodation,
                   String checkInDate, String checkOutDate, BookingStatus status) {
        this.bookingId = bookingId;
        this.user = user;
        this.accommodation = accommodation;
        this.checkInDate = LocalDate.parse(checkInDate);
        this.checkOutDate = LocalDate.parse(checkOutDate);
        this.status = status;
        this.confirmedDate = LocalDate.now().toString();  // FIXED: Use confirmedDate, not bookingDate
        this.totalPrice = accommodation.getPricePerNight() * getNumberOfNights();
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

    @Override
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

    @Override
    public void setStatus(BookingStatus status) {
        if (status == null) {
            this.status = BookingStatus.CANCELLED;
        } else {
            this.status = status;
        }
    }

    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    private double calculateTotalPrice() {
        if (accommodation == null) return 0.0;
        return accommodation.getPricePerNight() * getNumberOfNights();
    }
   
    @Override
    public boolean canChangeTo(BookingStatus newStatus) {
        if (this.status == BookingStatus.CONFIRMED) {
            return newStatus == BookingStatus.CHECKED_IN || newStatus == BookingStatus.CANCELLED;
        } else if (this.status == BookingStatus.CHECKED_IN) {
            return newStatus == BookingStatus.CHECKED_OUT || newStatus == BookingStatus.CANCELLED;
        } else if (this.status == BookingStatus.CANCELLED) {
            return newStatus == BookingStatus.REFUNDED;
        }
        return false;
    }

    @Override
    public void display() {
        System.out.println("BOOKING DETAILS ");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("User: " + user.getName());
        System.out.println("Accommodation: " + accommodation.getName());
        System.out.println("Check-in: " + checkInDate);
        System.out.println("Check-out: " + checkOutDate);
        System.out.println("Nights: " + getNumberOfNights());
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Status: " + status);
        System.out.println("Confirmed Date: " + confirmedDate);
    }

    @Override
    public void displayName() {
        System.out.println("Booking #" + bookingId + " - " + user.getName() + " at " + accommodation.getName());
    }
}