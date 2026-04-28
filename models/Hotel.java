package models;

public class Hotel {
    private int hotelId;
    private String name;
    private double pricePerNight;
    private int capacity;
    private int stars;
    private boolean hasBreakfast;
    private boolean hasPool;

    public Hotel(int hotelId, String name, double pricePerNight, int capacity, int stars, boolean hasBreakfast, boolean hasPool) {
        this.hotelId = hotelId;
        setName(name);
        setPricePerNight(pricePerNight);
        setCapacity(capacity);
        setStars(stars);
        setHasBreakfast(hasBreakfast);
        setHasPool(hasPool);
    }

    public int getHotelId() { return hotelId; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public int getStars() { return stars; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isHasBreakfast() { return hasBreakfast; }
    public boolean isHasPool() { return hasPool; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Hotel name cannot be empty. Using default name.");
            this.name = "Unknown Hotel";
        } else {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight < 0) {
            System.out.println("Price cannot be negative. Setting to default $100.");
            this.pricePerNight = 100.0;
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

    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            System.out.println("Stars must be between 1 and 5. Setting to default 3 stars.");
            this.stars = 3;
        } else {
            this.stars = stars;
        }
    }

    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
    public void setHasPool(boolean hasPool) { this.hasPool = hasPool; }
}