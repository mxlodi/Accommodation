import java.time.LocalDateTime;
public class Payment {
    // ATTRIBUTES only
    int paymentId;
    Booking booking;
    double amount;
    String method;      // "CASH", "CARD", "ONLINE" as String
    LocalDateTime paymentDate;
    boolean completed;
}