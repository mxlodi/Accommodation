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
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Hotel";
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight >= 0) {
            this.pricePerNight = pricePerNight;
        } else {
            this.pricePerNight = 100.0;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 1;
        }
    }

    public void setStars(int stars) {
        if (stars >= 1 && stars <= 5) {
            this.stars = stars;
        } else {
            this.stars = 3;
        }
    }

    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
    public void setHasPool(boolean hasPool) { this.hasPool = hasPool; }
}
