package models;

public class Apartment {
    private int apartmentId;
    private String name;
    private double pricePerNight;
    private int capacity;
    private int floor;
    private boolean hasElevator;
    private boolean hasBreakfast;

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

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Apartment name cannot be empty. Using default name.");
            this.name = "Unknown Apartment";
        } else {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight < 0) {
            System.out.println("Price cannot be negative. Setting to default $50.");
            this.pricePerNight = 50.0;
        } else {
            this.pricePerNight = pricePerNight;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            System.out.println("Capacity must be at least 1. Setting to 2.");
            this.capacity = 2;
        } else {
            this.capacity = capacity;
        }
    }

    public void setFloor(int floor) {
        if (floor <= 0) {
            System.out.println("Floor must be at least 1. Setting to 1.");
            this.floor = 1;
        } else {
            this.floor = floor;
        }
    }

    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }
    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
}