// package interfaces;

// public interface Payable {
//     void processPayment(String method);
//     boolean isConfirmed();
// }

package interfaces;

public interface Payable {
    void processPayment();
    double getAmount();
    boolean isPaid();
}