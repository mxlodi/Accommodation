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
    
    private List<Booking> bookings = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<GuestHouse> guestHouses = new ArrayList<>();
    private List<Apartment> apartments = new ArrayList<>();
    
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
            System.out.println("❌ User ID " + user.getUserId() + " already exists!");
        } else if (!validator.isValidEmail(user.getEmail())) {
            System.out.println("❌ Invalid email format for user: " + user.getName());
        } else {
            users.add(user);
            System.out.println("✅ User added: " + user.getName() + " (ID: " + user.getUserId() + ")");
        }
    }
    
    public List<models.User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    public void addHotel(models.Hotel hotel) {
        if (!validator.isValidPrice(hotel.getPricePerNight())) {
            System.out.println("❌ Invalid price for hotel: " + hotel.getName());
        } else if (!validator.isValidCapacity(hotel.getCapacity())) {
            System.out.println("❌ Invalid capacity for hotel: " + hotel.getName());
        } else if (!validator.isValidStars(hotel.getStars())) {
            System.out.println("❌ Invalid star rating for hotel: " + hotel.getName());
        } else {
            hotels.add(hotel);
            System.out.println("✅ Hotel added: " + hotel.getName() + " (⭐" + hotel.getStars() + ")");
        }
    }
    
    public void addGuestHouse(models.GuestHouse gh) {
        if (!validator.isValidPrice(gh.getPricePerNight())) {
            System.out.println("❌ Invalid price for guest house: " + gh.getName());
        } else if (!validator.isValidCapacity(gh.getCapacity())) {
            System.out.println("❌ Invalid capacity for guest house: " + gh.getName());
        } else {
            guestHouses.add(gh);
            String breakfast = gh.isHasBreakfast() ? "with breakfast" : "no breakfast";
            System.out.println("✅ Guest House added: " + gh.getName() + " (" + breakfast + ")");
        }
    }
    
    public void addApartment(models.Apartment apt) {
        if (!validator.isValidPrice(apt.getPricePerNight())) {
            System.out.println("❌ Invalid price for apartment: " + apt.getName());
        } else if (!validator.isValidCapacity(apt.getCapacity())) {
            System.out.println("❌ Invalid capacity for apartment: " + apt.getName());
        } else {
            apartments.add(apt);
            String elevator = apt.isHasElevator() ? "with elevator" : "stairs only";
            System.out.println("✅ Apartment added: " + apt.getName() + " (Floor " + apt.getFloor() + ", " + elevator + ")");
        }
    }
    
    public void addBooking(models.Booking booking) {
        if (booking == null) {
            System.out.println("❌ Booking cannot be null!");
            return;
        }
        
        if (booking.getUserId() == null) {
            System.out.println("❌ Booking must have a valid user!");
            return;
        }
        
        if (!validator.areDatesValid(booking.getCheckInDate(), booking.getCheckOutDate())) {
            System.out.println("❌ Invalid dates for booking: Check-in must be before check-out!");
            return;
        }
        
        if (!validator.isValidPrice(booking.getTotalPrice())) {
            System.out.println("❌ Invalid total price for booking!");
            return;
        }
        
        if (validator.isDuplicateBooking(booking.getBookingId(), bookings)) {
            System.out.println("❌ Booking ID " + booking.getBookingId() + " already exists!");
            return;
        }
        
        bookings.add(booking);
        System.out.println("✅ Booking confirmed: #" + booking.getBookingId() + " for " + booking.getUserId().getName());
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
            System.out.println("❌ Booking #" + bookingId + " not found!");
        } else if (booking.getStatus() == models.BookingStatus.CANCELLED) {
            System.out.println("❌ Cannot update cancelled booking #" + bookingId);
        } else {
            models.BookingStatus oldStatus = booking.getStatus();
            booking.setStatus(newStatus);
            System.out.println("✅ Booking #" + bookingId + " status: " + oldStatus + " → " + newStatus);
        }
    }
    
    public void displayAllData() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 SYSTEM DATA OVERVIEW");
        System.out.println("=".repeat(60));
        
        System.out.println("\n👥 USERS (" + users.size() + "):");
        if (users.isEmpty()) {
            System.out.println("   No users found.");
        } else {
            for (models.User u : users) {
                System.out.println("   📌 ID:" + u.getUserId() + " | " + u.getName() + " | " + u.getEmail());
            }
        }
        
        System.out.println("\n🏨 HOTELS (" + hotels.size() + "):");
        if (hotels.isEmpty()) {
            System.out.println("   No hotels found.");
        } else {
            for (models.Hotel h : hotels) {
                String stars = "";
                for (int i = 0; i < h.getStars(); i++) stars += "⭐";
                System.out.println("   📌 ID:" + h.getHotelId() + " | " + h.getName() + " | " + stars + " | $" + h.getPricePerNight());
            }
        }
        
        System.out.println("\n🏠 GUEST HOUSES (" + guestHouses.size() + "):");
        if (guestHouses.isEmpty()) {
            System.out.println("   No guest houses found.");
        } else {
            for (models.GuestHouse gh : guestHouses) {
                String breakfast = gh.isHasBreakfast() ? "☕ Breakfast incl." : "No breakfast";
                System.out.println("   📌 ID:" + gh.getGuestHouseId() + " | " + gh.getName() + " | $" + gh.getPricePerNight() + " | " + breakfast);
            }
        }
        
        System.out.println("\n🏢 APARTMENTS (" + apartments.size() + "):");
        if (apartments.isEmpty()) {
            System.out.println("   No apartments found.");
        } else {
            for (models.Apartment a : apartments) {
                String elevator = a.isHasElevator() ? "Elevator" : "Stairs";
                System.out.println("   📌 ID:" + a.getApartmentId() + " | " + a.getName() + " | Floor " + a.getFloor() + " | " + elevator);
            }
        }
        
        System.out.println("\n📅 BOOKINGS (" + bookings.size() + "):");
        double totalRevenue = 0;
        for (models.Booking b : bookings) {
            String statusIcon;
            if (b.getStatus() == models.BookingStatus.CONFIRMED) {
                statusIcon = "✅";
            } else if (b.getStatus() == models.BookingStatus.CHECKED_IN) {
                statusIcon = "🏨";
            } else if (b.getStatus() == models.BookingStatus.CANCELLED) {
                statusIcon = "❌";
            } else {
                statusIcon = "📋";
            }
            
            System.out.println("   " + statusIcon + " #" + b.getBookingId() + " | " + b.getUserId().getName() + " | " + b.getCheckInDate() + " → " + b.getCheckOutDate() + " | $" + b.getTotalPrice());
            
            if (b.getStatus() != models.BookingStatus.CANCELLED && b.getStatus() != models.BookingStatus.REFUNDED) {
                totalRevenue += b.getTotalPrice();
            }
        }
        System.out.println("\n   💰 Total Revenue: $" + totalRevenue);
    }
}