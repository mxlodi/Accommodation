package models;

import interfaces.Displayable;
import interfaces.Bookable;
import java.time.LocalDate;
import java.util.Collection;

public abstract class Accommodation implements Displayable, Bookable {
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

    // Getters
    public int getAccId() {
        return accId;
    }

    public String getName() {
        return name;
    }

    // Bookable interface methods
    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            this.name = "Unknown Accommodation";
        } else {
            this.name = name;
        }
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight <= 0) {
            this.pricePerNight = 50.0;
        } else {
            this.pricePerNight = pricePerNight;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity < 1) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
    }

    // calculate the total price
    public double calculatePrice(int nights) {
        return pricePerNight * nights;
    }

    // Abstract method for subclasses
    public abstract String getType();

    // Displayable interface methods
    public void display() {
        System.out.println("ID: " + accId);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + pricePerNight + "/night");
        System.out.println("Capacity: " + capacity + " persons");
        System.out.println("Type: " + getType());
    }

    public void displayName() {
        System.out.println(name);
    }

    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut, Collection<Booking> allBookings) {
        for (Booking b : allBookings) {
            if (b.getAccommodation().getAccId() == this.accId) {
                if (b.isActive()) { 
                    LocalDate existingIn = LocalDate.parse(b.getCheckInDate());
                    LocalDate existingOut = LocalDate.parse(b.getCheckOutDate());
                    if (checkIn.isBefore(existingOut) && checkOut.isAfter(existingIn)) {
                        System.out.println("[REJECTED] Overlaps with active booking #" + b.getBookingId());
                        return false;
                    }
                }
            }
        }
        return true;
    }

}