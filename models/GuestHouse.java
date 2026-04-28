package models;

public class GuestHouse {
    private int guestHouseId;
    private String name;
    private double pricePerNight;
    private int capacity;
    private boolean hasBreakfast;
    private boolean hasElevator;

    public GuestHouse(int guestHouseId, String name, double pricePerNight, int capacity, boolean hasBreakfast, boolean hasElevator) {
        this.guestHouseId = guestHouseId;
        setName(name);
        setPricePerNight(pricePerNight);
        setCapacity(capacity);
        setHasBreakfast(hasBreakfast);
        setHasElevator(hasElevator);
    }

    public int getGuestHouseId() { return guestHouseId; }
    public String getName() { return name; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }
    public boolean isHasBreakfast() { return hasBreakfast; }
    public boolean isHasElevator() { return hasElevator; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Guest house name cannot be empty. Using default name.");
            this.name = "Unknown Guest House";
        } else {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight < 0) {
            System.out.println("Price cannot be negative. Setting to default $30.");
            this.pricePerNight = 30.0;
        } else {
            this.pricePerNight = pricePerNight;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            System.out.println("Capacity must be at least 1. Setting to 1.");
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
    }

    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }
}