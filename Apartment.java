
public class Apartment {
    int apartmentId;
    String name;
    double pricePerNight;
    int capacity;
    int floor;
    boolean hasElevator;
    boolean hasBreakfast;
    boolean hasPool;

    public Apartment(int apartmentId, String name, double pricePerNight, int capacity, int floor, boolean hasElevator, boolean hasBreakfast) {
        this.apartmentId = apartmentId;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.floor = floor;
        this.hasElevator = hasElevator;
        this.hasBreakfast = hasBreakfast;
    }

    //testing git

    public int getApartmentId() {
        return apartmentId;
    }

    public String getName() {
        return name;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    //SETTER
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight > 0) {
            this.pricePerNight = pricePerNight;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    public void setFloor(int floor) {
        if (floor > 0) {
            this.floor = floor;
        }
    }

    public void setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }
    

}