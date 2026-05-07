package models;

// GuestHouse extends from Accommodation and adds only guest-house specific fields:
// hasBreakfast, hasElevator

public class GuestHouse extends Accommodation implements Displayable {
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
    public String getSummary() {
        String breakfast = hasBreakfast ? "Breakfast incl." : "No breakfast";
        String elevator = hasElevator ? "Elevator" : "Stairs only";
        return "[GUEST HOUSE] " + getName() + " | $" + getPricePerNight()
                + "/night | " + breakfast + " | " + elevator;
    }
}
