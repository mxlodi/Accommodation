
package interfaces;

public interface Bookable {
    double calculatePrice(int nights);
    int getCapacity();
    double getPricePerNight();
}