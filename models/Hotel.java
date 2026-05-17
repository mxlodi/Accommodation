package models;

public class Hotel extends Accommodation {
    private int stars;
    private boolean hasPool;

    public Hotel(int id, String name, double price, int capacity, int stars, boolean pool) {
        super(id, name, price, capacity); // Pass common data to parent
        this.stars = stars;
        this.hasPool = pool;
    }

    @Override
    public String getType() { return "HOTEL"; }

    @Override
    public void display() {
        System.out.println("HOTEL: " + getName() + " (" + stars + " Stars)");
        System.out.println("Price: $" + getPricePerNight() + "/night | Pool: " + (hasPool ? "Yes" : "No"));
    }

    @Override
    public void displayName() { System.out.println("Hotel: " + getName()); }
}