package models;

public class Apartment {
    private int apartmentId;
    private String name;
    private double pricePerNight;
    private int capacity;
    private int floor;
    private boolean hasElevator;
    private boolean hasBreakfast;
    private boolean hasPool;

    public Apartment(int apartmentId, String name, double pricePerNight, int capacity, int floor, boolean hasElevator, boolean hasBreakfast) {
        this.apartmentId = apartmentId;
        setName(name);
        setPricePerNight(pricePerNight);
        setCapacity(capacity);
        setFloor(floor);
        setHasElevator(hasElevator);
        setHasBreakfast(hasBreakfast);
    }

    public int getApartmentId() { return apartmentId; }
    public String getName() { return name; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }
    public int getFloor() { return floor; }
    public boolean isHasElevator() { return hasElevator; }
    public boolean isHasBreakfast() { return hasBreakfast; }
    public boolean isHasPool() { return hasPool; }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Apartment";
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight > 0) {
            this.pricePerNight = pricePerNight;
        } else {
            this.pricePerNight = 50.0;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 2;
        }
    }

    public void setFloor(int floor) {
        if (floor > 0) {
            this.floor = floor;
        } else {
            this.floor = 1;
        }
    }

    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }
    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
    public void setHasPool(boolean hasPool) { this.hasPool = hasPool; }
}