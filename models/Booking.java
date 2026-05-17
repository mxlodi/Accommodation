package models;

import interfaces.StatusChangeable;
import interfaces.Displayable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking implements StatusChangeable, Displayable {
    private int bookingId;
    private User user;
    private Accommodation accommodation;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;

    public Booking(int id, User user, Accommodation acc, String in, String out, BookingStatus status) {
        this.bookingId = id;
        this.user = user;
        this.accommodation = acc;
        this.checkInDate = LocalDate.parse(in);
        this.checkOutDate = LocalDate.parse(out);
        this.status = status;
    }

    // Fixed: Restored isValidDates() for the Repository
    public boolean isValidDates() {
        return checkOutDate != null && checkInDate != null && checkOutDate.isAfter(checkInDate);
    }

    public int getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Accommodation getAccommodation() { return accommodation; }
    public String getCheckInDate() { return checkInDate.toString(); }
    public String getCheckOutDate() { return checkOutDate.toString(); }

    @Override
    public BookingStatus getStatus() { return status; }

    @Override
    public void setStatus(BookingStatus status) { this.status = status; }

    @Override
    public boolean canChangeTo(BookingStatus newStatus) {
        if (this.status == BookingStatus.CONFIRMED) {
            return newStatus == BookingStatus.CHECKED_IN || newStatus == BookingStatus.CANCELLED;
        }
        return false;
    }

    public double calculateTotalPrice() {
        if (accommodation == null) return 0.0;
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return accommodation.calculatePrice((int) nights);
    }

    public boolean isActive() {
        return status == BookingStatus.CONFIRMED || status == BookingStatus.CHECKED_IN;
    }

    @Override
    public void display() {
        System.out.println("Booking ID: " + bookingId + " | Status: " + status);
    }

    @Override
    public void displayName() {
        System.out.println("Booking #" + bookingId);
    }
}