package models;

import java.time.LocalDateTime;
import interfaces.Displayable;
import interfaces.Payable;

public class Payment implements Displayable, Payable {
    private int paymentId;
    private Booking booking;
    private double amount;
    private String method;
    private LocalDateTime paymentDate;
    private boolean completed;

    public Payment(int paymentId, Booking booking, String method) {
        this.paymentId = paymentId;
        setBooking(booking);
        setMethod(method);
        this.amount = (booking != null) ? booking.getTotalPrice() : 0.0;
        this.paymentDate = LocalDateTime.now();
        this.completed = false; // Payment starts as PENDING
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
        if (!completed) {
            this.completed = true;
            System.out.println("Payment #" + paymentId + " processed: $" + amount);
        } else {
            System.out.println("Payment #" + paymentId + " already completed!");
        }
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

    // Process refund
    public void processRefund() {
        if (completed) {
            System.out.println("Refunding $" + amount + " for payment #" + paymentId);
            completed = false;
        } else {
            System.out.println("Payment #" + paymentId + " is not completed. Cannot refund.");
        }
    }

    // Validate payment method is acceptable
    public boolean isValidMethod() {
        return method.equals("CASH") || method.equals("CARD") || method.equals("ONLINE");
    }

    // Change payment method after creation (with validation)
    public boolean changeMethod(String newMethod) {
        if (newMethod != null && (newMethod.equals("CASH") || newMethod.equals("CARD") || newMethod.equals("ONLINE"))) {
            this.method = newMethod;
            System.out.println("Payment method changed to: " + newMethod);
            return true;
        }
        System.out.println("[ERROR] Invalid payment method: " + newMethod);
        return false;
    }
}