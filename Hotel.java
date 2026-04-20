public class Hotel {
    int hotelId;
    String name;
    double pricePerNight;
    int capacity;
    int stars;
    boolean hasBreakfast;
    boolean hasPool;

    // CONSTRUCTOR
    public Hotel(int hotelId, String name, double pricePerNight, int capacity, int stars, boolean hasBreakfast, boolean hasPool) {
        this.hotelId = hotelId;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.stars = stars;
        this.hasBreakfast = hasBreakfast;
        this.hasPool = hasPool;
    }

    // GETTER
    public int getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
    public int getStars() {
        return stars;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public boolean isHasBreakfast() {
        return hasBreakfast;
    }
    public boolean isHasPool() {
        return hasPool;
    }


    // SETTER
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }
    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight >= 0) {
            this.pricePerNight = pricePerNight;
        }
    }
    
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }
    
    public void setStars(int stars) {
        if (stars >= 1 && stars <= 5) {
            this.stars = stars;
        }
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }
}
