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
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be empty. Using default name.");
            this.name = "Unknown Accommodation";
        } else {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight < 0) {
            System.out.println("Price cannot be negative. Setting to 0.");
            this.pricePerNight = 0.0;
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
}