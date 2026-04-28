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
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Guest House";
        }
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 1;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight >= 0) {
            this.pricePerNight = pricePerNight;
        } else {
            this.pricePerNight = 30.0;
        }
    }

    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }
}