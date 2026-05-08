package models;

import java.time.LocalDateTime;
import interfaces.Displayable;
import interfaces.Payable;

public class Payment implements Displayable, Payable {
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

    @Override
    public boolean isPaid() {
        return completed;
    }

    public void setBooking(Booking booking) {
        if (booking != null) {
            this.booking = booking;
        } else {
            this.booking = null;
        }
    }

    public void setMethod(String method) {
        if (method != null && (method.equals("CASH") || method.equals("CARD") || method.equals("ONLINE"))) {
            this.method = method;
        } else {
            this.method = "CASH";
        }
    }

    @Override
    public void processPayment() {
        this.completed = true;
        System.out.println("Payment #" + paymentId + " processed: $" + amount);
    }

    @Override
    public void display() {
        System.out.println("=> PAYMENT DETAILS ");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Booking ID: " + (booking != null ? booking.getBookingId() : "N/A"));
        System.out.println("Amount: $" + amount);
        System.out.println("Method: " + method);
        System.out.println("Date: " + paymentDate.toLocalDate());
        System.out.println("Status: " + (completed ? "COMPLETED" : "PENDING"));
    }

    @Override
    public void displayName() {
        System.out.println("Payment #" + paymentId + " - $" + amount + " (" + method + ")");
    }
}