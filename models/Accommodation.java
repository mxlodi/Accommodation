package models;
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

    public int getAccId() { return accId; }
    public String getName() { return name; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Accommodation";
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight >= 0) {
            this.pricePerNight = pricePerNight;
        } else {
            this.pricePerNight = 0.0;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 1;
        }
    }
}