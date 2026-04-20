public class Accommodation {
    // ATTRIBUTES only
    private int accId;
    private String name;
    private double pricePerNight;
    private int capacity;

    // CONSTRUCTOR
    public Accommodation(int accId, String name, double pricePerNight, int capacity) {
        this.accId = accId;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
    }

    // GETTERS
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

    // SETTERS
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

}