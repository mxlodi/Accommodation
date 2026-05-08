package interfaces;

public interface Payable {
    void processPayment();
    double getAmount();
    boolean isPaid();
}