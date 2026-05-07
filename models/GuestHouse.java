package models;

// GuestHouse extends from Accommodation and adds only guest-house specific fields:
// hasBreakfast, hasElevator

public class GuestHouse extends Accommodation {
    private boolean hasBreakfast;
    private boolean hasElevator;

    public GuestHouse(int guestHouseId, String name, double pricePerNight, int capacity,
            boolean hasBreakfast, boolean hasElevator) {
        super(guestHouseId, name, pricePerNight, capacity);
        this.hasBreakfast = hasBreakfast;
        this.hasElevator = hasElevator;
    }

    public int getGuestHouseId() {
        return getAccId();
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    @Override
    public String getType() {
        return "GUEST_HOUSE";
    }

    @Override
    public void display() {
        System.out.println("=== GUEST HOUSE DETAILS ===");
        System.out.println("ID: " + getAccId());
        System.out.println("Name: " + getName());
        System.out.println("Price: $" + getPricePerNight() + "/night");
        System.out.println("Capacity: " + getCapacity() + " persons");
        System.out.println("Breakfast: " + (hasBreakfast ? "Yes" : "No"));
        System.out.println("Elevator: " + (hasElevator ? "Yes" : "No"));
    }
}
