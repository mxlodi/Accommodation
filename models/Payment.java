package models;
import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private Booking booking;
    private double amount;
    private String method;
    private LocalDateTime paymentDate;
    private boolean completed;

    public Payment(int paymentId, Booking booking, double amount, String method, LocalDateTime paymentDate, boolean completed) {
        this.paymentId = paymentId;
        setBooking(booking);
        setAmount(amount);
        setMethod(method);
        setPaymentDate(paymentDate);
        setCompleted(completed);
    }

    public int getPaymentId() { return paymentId; }
    public Booking getBooking() { return booking; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public boolean isCompleted() { return completed; }

    public void setPaymentId(int paymentId) {
        if (paymentId > 0) {
            this.paymentId = paymentId;
        } else {
            this.paymentId = 0;
        }
    }

    public void setBooking(Booking booking) {
        if (booking != null) {
            this.booking = booking;
        } else {
            this.booking = null;
        }
    }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            this.amount = 0.0;
        }
    }

    public void setMethod(String method) {
        if (method != null && (method.equals("CASH") || method.equals("CARD") || method.equals("ONLINE"))) {
            this.method = method;
        } else {
            this.method = "CASH";
        }
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        if (paymentDate != null) {
            this.paymentDate = paymentDate;
        } else {
            this.paymentDate = LocalDateTime.now();
        }
    }

    public void setCompleted(boolean completed) { this.completed = completed; }
}