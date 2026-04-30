package models;

// Accommodation holds only COMMON fields shared by Hotel, GuestHouse, Apartment
// Hotel / GuestHouse / Apartment each hold ONLY their own special fields
// Booking stores ONE Accommodation reference

public class Accommodation {
    private int accId;
    private String name;
    private double pricePerNight;
    private int capacity;

    public Accommodation(int accId, String name, double pricePerNight, int capacity) {
        this.accId = accId;
        setName(name);
        setPricePerNight(pricePerNight);
        setCapacity(capacity);
    }

    public int getAccId() {
        return accId;
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

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("  [WARNING] Accommodation name cannot be empty. Using default name.");
            this.name = "Unknown Accommodation";
        } else {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight <= 0) {
            System.out.println("  [WARNING] Price must be positive. Setting to default $50.");
            this.pricePerNight = 50.0;
        } else {
            this.pricePerNight = pricePerNight;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity < 1) {
            System.out.println("  [WARNING] Capacity must be at least 1. Setting to 1.");
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
    }

    // Use for display extra detail
    public String getSummary() {
        return getName() + " | $" + getPricePerNight() + "/night | Capacity: " + getCapacity();
    }
}