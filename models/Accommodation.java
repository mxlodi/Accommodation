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
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
    }

    // Abstract method: Every specific type must identify itself
    public abstract String getType();

    public double calculatePrice(int nights) {
        return pricePerNight * nights;
    }

    // Common Getters
    public int getAccId() { return accId; }
    public String getName() { return name; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }

    // Shared business logic: Check if dates overlap with existing bookings
    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut, Collection<Booking> allBookings) {
        for (Booking b : allBookings) {
            if (b.getAccommodation().getAccId() == this.accId && b.isActive()) {
                LocalDate existingIn = LocalDate.parse(b.getCheckInDate());
                LocalDate existingOut = LocalDate.parse(b.getCheckOutDate());
                if (checkIn.isBefore(existingOut) && checkOut.isAfter(existingIn)) {
                    return false;
                }
            }
        }
        return true;
    }
}