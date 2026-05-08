package models;

// Hotel extends Accommodation and adds only hotel specific fields:
// stars, hasBreakfast, hasPool
// name, pricePerNight, capacity are handled by Accommodation

public class Hotel extends Accommodation {
    private int stars;
    private boolean hasBreakfast;
    private boolean hasPool;

    public Hotel(int hotelId, String name, double pricePerNight, int capacity,
            int stars, boolean hasBreakfast, boolean hasPool) {
        super(hotelId, name, pricePerNight, capacity);
        setStars(stars);
        this.hasBreakfast = hasBreakfast;
        this.hasPool = hasPool;
    }

    public int getHotelId() {
        return getAccId();
    }

    public int getStars() {
        return stars;
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            System.out.println("  [WARNING] Stars must be 1–5. Setting to default 3.");
            this.stars = 3;
        } else {
            this.stars = stars;
        }
    }

    @Override
    public String getType() {
        return "HOTEL";
    }

    @Override
    public void display() {
        System.out.println("- HOTEL DETAILS -");
        System.out.println("ID: " + getAccId());
        System.out.println("Name: " + getName());
        System.out.println("Stars: " + stars + "-star");
        System.out.println("Price: $" + getPricePerNight() + "/night");
        System.out.println("Capacity: " + getCapacity() + " persons");
        System.out.println("Breakfast: " + (hasBreakfast ? "Yes" : "No"));
        System.out.println("Pool: " + (hasPool ? "Yes" : "No"));
    }
}
