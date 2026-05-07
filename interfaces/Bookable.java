// package interfaces;
// public interface Bookable {
//     boolean isAvailable(java.time.LocalDate start, java.time.LocalDate end);
//     double calculateTotalPrice();
// }

package interfaces;

public interface Bookable {
    double calculatePrice(int nights);
    int getCapacity();
    double getPricePerNight();
}