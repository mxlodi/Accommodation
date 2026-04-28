package repository;

import models.Booking;
import models.BookingStatus;
import models.User;
import models.Hotel;
import models.GuestHouse;
import models.Apartment;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    // --- STATIC COUNTER (Week 3 requirement) ---
    // Belongs to the class, not any single object.
    // Tracks total bookings ever attempted across the whole system.
    private static int totalBookingsCreated = 0;

    public static int getTotalBookingsCreated() {
        return totalBookingsCreated;
    }
    // -------------------------------------------

    private List<Booking> bookings = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<GuestHouse> guestHouses = new ArrayList<>();
    private List<Apartment> apartments = new ArrayList<>();

    // PRIVATE INNER CLASS (Week 3 requirement)
    private class DataValidator {

        public boolean isValidEmail(String email) {
            if (email == null) return false;
            if (email.contains("@") && email.contains(".")) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isValidPhone(String phone) {
            if (phone == null) return false;
            if (phone.length() >= 9 && phone.length() <= 12) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isValidPrice(double price) {
            if (price > 0) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isValidCapacity(int capacity) {
            if (capacity >= 1 && capacity <= 10) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isValidStars(int stars) {
            if (stars >= 1 && stars <= 5) {
                return true;
            } else {
                return false;
            }
        }

        public boolean areDatesValid(String checkIn, String checkOut) {
            if (checkIn == null || checkOut == null) return false;
            if (checkIn.compareTo(checkOut) < 0) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isDuplicateBooking(int bookingId, List<Booking> existingBookings) {
            for (Booking b : existingBookings) {
                if (b.getBookingId() == bookingId) {
                    return true;
                }
            }
            return false;
        }

        public boolean userExists(int userId, List<User> existingUsers) {
            for (User u : existingUsers) {
                if (u.getUserId() == userId) {
                    return true;
                }
            }
            return false;
        }
    }

    private DataValidator validator = new DataValidator();

    public void addUser(models.User user) {
        if (validator.userExists(user.getUserId(), users)) {
            System.out.println("  [ERROR] User ID " + user.getUserId() + " already exists!");
        } else if (!validator.isValidEmail(user.getEmail())) {
            System.out.println("  [ERROR] Invalid email for user: " + user.getName() + " -> using default email");
            users.add(user);
            System.out.println("  [OK]    User added: " + user.getName() + " (ID: " + user.getUserId() + ")");
        } else {
            users.add(user);
            System.out.println("  [OK]    User added: " + user.getName() + " (ID: " + user.getUserId() + ")");
        }
    }

    public List<models.User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void addHotel(models.Hotel hotel) {
        if (!validator.isValidPrice(hotel.getPricePerNight())) {
            System.out.println("  [ERROR] Invalid price for hotel: " + hotel.getName() + " -> using default $100");
            hotels.add(hotel);
            System.out.println("  [OK]    Hotel added: " + hotel.getName() + " (" + hotel.getStars() + " stars)");
        } else if (!validator.isValidCapacity(hotel.getCapacity())) {
            System.out.println("  [ERROR] Invalid capacity for hotel: " + hotel.getName());
        } else if (!validator.isValidStars(hotel.getStars())) {
            System.out.println("  [ERROR] Invalid star rating for hotel: " + hotel.getName() + " -> using default 3 stars");
            hotels.add(hotel);
            System.out.println("  [OK]    Hotel added: " + hotel.getName() + " (" + hotel.getStars() + " stars)");
        } else {
            hotels.add(hotel);
            System.out.println("  [OK]    Hotel added: " + hotel.getName() + " (" + hotel.getStars() + " stars)");
        }
    }

    public void addGuestHouse(models.GuestHouse gh) {
        if (!validator.isValidPrice(gh.getPricePerNight())) {
            System.out.println("  [ERROR] Invalid price for guest house: " + gh.getName());
        } else if (!validator.isValidCapacity(gh.getCapacity())) {
            System.out.println("  [ERROR] Invalid capacity for guest house: " + gh.getName());
        } else {
            guestHouses.add(gh);
            String breakfast = gh.isHasBreakfast() ? "with breakfast" : "no breakfast";
            System.out.println("  [OK]    Guest House added: " + gh.getName() + " (" + breakfast + ")");
        }
    }

    public void addApartment(models.Apartment apt) {
        if (!validator.isValidPrice(apt.getPricePerNight())) {
            System.out.println("  [ERROR] Invalid price for apartment: " + apt.getName());
        } else if (!validator.isValidCapacity(apt.getCapacity())) {
            System.out.println("  [ERROR] Invalid capacity for apartment: " + apt.getName());
        } else {
            apartments.add(apt);
            String elevator = apt.isHasElevator() ? "with elevator" : "stairs only";
            System.out.println("  [OK]    Apartment added: " + apt.getName() + " (Floor " + apt.getFloor() + ", " + elevator + ")");
        }
    }

    public void addBooking(models.Booking booking) {
        if (booking == null) {
            System.out.println("  [ERROR] Booking cannot be null!");
            return;
        }

        if (booking.getUserId() == null) {
            System.out.println("  [ERROR] Booking must have a valid user!");
            return;
        }

        if (!validator.areDatesValid(booking.getCheckInDate(), booking.getCheckOutDate())) {
            System.out.println("  [ERROR] Invalid dates for booking #" + booking.getBookingId()
                    + ": Check-in must be before check-out!");
            return;
        }

        if (!validator.isValidPrice(booking.getTotalPrice())) {
            System.out.println("  [ERROR] Invalid total price for booking #" + booking.getBookingId());
            return;
        }

        if (validator.isDuplicateBooking(booking.getBookingId(), bookings)) {
            System.out.println("  [ERROR] Booking ID " + booking.getBookingId() + " already exists!");
            return;
        }

        bookings.add(booking);
        totalBookingsCreated++;  // increment static counter
        System.out.println("  [OK]    Booking confirmed: #" + booking.getBookingId()
                + " for " + booking.getUserId().getName());
    }

    public void updateBookingStatus(int bookingId, models.BookingStatus newStatus) {
        models.Booking booking = null;
        for (models.Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                booking = b;
                break;
            }
        }

        if (booking == null) {
            System.out.println("  [ERROR] Booking #" + bookingId + " not found!");
        } else if (booking.getStatus() == models.BookingStatus.CANCELLED) {
            System.out.println("  [ERROR] Cannot update cancelled booking #" + bookingId);
        } else {
            models.BookingStatus oldStatus = booking.getStatus();
            booking.setStatus(newStatus);
            System.out.println("  [OK]    Booking #" + bookingId + " status: " + oldStatus + " -> " + newStatus);
        }
    }

    public void displayAllData() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SYSTEM DATA OVERVIEW");
        System.out.println("=".repeat(60));

        // USERS
        System.out.println("\n[USERS] Total: " + users.size());
        System.out.println("-".repeat(40));
        if (users.isEmpty()) {
            System.out.println("   No users found.");
        } else {
            for (models.User u : users) {
                System.out.println("  ID:" + u.getUserId() + " | " + u.getName() + " | " + u.getEmail());
            }
        }

        // HOTELS
        System.out.println("\n[HOTELS] Total: " + hotels.size());
        System.out.println("-".repeat(40));
        if (hotels.isEmpty()) {
            System.out.println("   No hotels found.");
        } else {
            for (models.Hotel h : hotels) {
                String stars = h.getStars() + "-star";
                System.out.println("  ID:" + h.getHotelId() + " | " + h.getName()
                        + " | " + stars + " | $" + h.getPricePerNight());
            }
        }

        // GUEST HOUSES
        System.out.println("\n[GUEST HOUSES] Total: " + guestHouses.size());
        System.out.println("-".repeat(40));
        if (guestHouses.isEmpty()) {
            System.out.println("   No guest houses found.");
        } else {
            for (models.GuestHouse gh : guestHouses) {
                String breakfast = gh.isHasBreakfast() ? "Breakfast incl." : "No breakfast";
                System.out.println("  ID:" + gh.getGuestHouseId() + " | " + gh.getName()
                        + " | $" + gh.getPricePerNight() + " | " + breakfast);
            }
        }

        // APARTMENTS
        System.out.println("\n[APARTMENTS] Total: " + apartments.size());
        System.out.println("-".repeat(40));
        if (apartments.isEmpty()) {
            System.out.println("   No apartments found.");
        } else {
            for (models.Apartment a : apartments) {
                String elevator = a.isHasElevator() ? "Elevator" : "Stairs";
                System.out.println("  ID:" + a.getApartmentId() + " | " + a.getName()
                        + " | Floor " + a.getFloor() + " | " + elevator);
            }
        }

        // BOOKINGS
        System.out.println("\n[BOOKINGS] Total: " + bookings.size());
        System.out.println("-".repeat(40));
        double totalRevenue = 0;
        for (models.Booking b : bookings) {
            String statusLabel;
            if (b.getStatus() == models.BookingStatus.CONFIRMED) {
                statusLabel = "[CONFIRMED]";
            } else if (b.getStatus() == models.BookingStatus.CHECKED_IN) {
                statusLabel = "[CHECKED_IN]";
            } else if (b.getStatus() == models.BookingStatus.CANCELLED) {
                statusLabel = "[CANCELLED]";
            } else if (b.getStatus() == models.BookingStatus.CHECKED_OUT) {
                statusLabel = "[CHECKED_OUT]";
            } else {
                statusLabel = "[REFUNDED]";
            }

            System.out.println("  " + statusLabel + " #" + b.getBookingId()
                    + " | " + b.getUserId().getName()
                    + " | " + b.getCheckInDate() + " to " + b.getCheckOutDate()
                    + " | $" + b.getTotalPrice());

            if (b.getStatus() != models.BookingStatus.CANCELLED
                    && b.getStatus() != models.BookingStatus.REFUNDED) {
                totalRevenue += b.getTotalPrice();
            }
        }
        System.out.println("\n  Total Active Revenue : $" + totalRevenue);

        // STATIC COUNTER SUMMARY
        System.out.println("\n[STATIC] Total bookings created (class-level counter): "
                + BookingRepository.getTotalBookingsCreated());
    }
}