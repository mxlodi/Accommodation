package models;

import java.time.LocalDateTime;

// Payment is connected to Booking
// One Booking have one Payment
// BookingRepository manages both Booking and Payment 

public class Payment {
    private int paymentId;
    private Booking booking; 
    // Association: Payment knows which Booking it covers
    private double amount;
    private String method;
    private LocalDateTime paymentDate;
    private boolean completed;

    public Payment(int paymentId, Booking booking, String method) {
        this.paymentId = paymentId;
        setBooking(booking);
        setMethod(method);
        // Amount is taken directly from the booking's calculated totalPrice
        this.amount = (booking != null) ? booking.getTotalPrice() : 0.0;
        this.paymentDate = LocalDateTime.now();
        this.completed = true;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public int getPaymentId() {
        return paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    // ── Setters ───────────────────────────────────────────────────────────────
    public void setBooking(Booking booking) {
        if (booking != null) {
            this.booking = booking;
        } else {
            System.out.println("  [WARNING] Payment must be linked to a valid Booking.");
            this.booking = null;
        }
    }

    public void setMethod(String method) {
        if (method != null && (method.equals("CASH") || method.equals("CARD") || method.equals("ONLINE"))) {
            this.method = method;
        } else {
            System.out.println("  [WARNING] Invalid payment method '" + method + "'. Using CASH.");
            this.method = "CASH";
        }
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}