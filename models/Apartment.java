package models;

// Apartment extends from Accommodation, adds ONLY apartment specific fields:
// floor, hasElevator, hasBreakfast, hasPool

public class Apartment extends Accommodation {
    private int floor;
    private boolean hasElevator;
    private boolean hasBreakfast;
    private boolean hasPool;

    public Apartment(int apartmentId, String name, double pricePerNight, int capacity,
            int floor, boolean hasElevator, boolean hasBreakfast) {
        super(apartmentId, name, pricePerNight, capacity);
        setFloor(floor);
        this.hasElevator = hasElevator;
        this.hasBreakfast = hasBreakfast;
        this.hasPool = false; // default
    }

    public int getApartmentId() {
        return getAccId();
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

    public void setFloor(int floor) {
        if (floor < 1) {
            System.out.println("  [WARNING] Floor must be at least 1. Setting to 1.");
            this.floor = 1;
        } else {
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

    // @Override - replaces the getSummary() method from the parent Accommodation class
    // We override because Apartment has extra information (floor number, elevator) to display
    @Override
    public String getSummary() {
        String elevator = hasElevator ? "Elevator" : "Stairs only";
        return "[APARTMENT] " + getName() + " | Floor " + floor + " | $" + getPricePerNight()
                + "/night | " + elevator;
    }
}