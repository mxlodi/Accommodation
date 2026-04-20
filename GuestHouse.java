public class GuestHouse {
    int guestHouseId;
    String name;
    double pricePerNight;
    int capacity;
    boolean hasBreakfast;
    boolean hasElevator;


    //CONSTRUCTOR
    public GuestHouse(int guestHouseId, String name, double pricePerNight, int capacity, boolean hasBreakfast, boolean hasElevator) {
        this.guestHouseId = guestHouseId;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.hasBreakfast = hasBreakfast;
        this.hasElevator = hasElevator;
    }

    //GETTER
    public int getGuestHouseId() {
        return guestHouseId;
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

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }


    // SETTER
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }
    
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight >= 0) {
            this.pricePerNight = pricePerNight;
        }
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
    }
    
}