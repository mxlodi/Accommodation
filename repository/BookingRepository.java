
package repository;

import models.*;
import java.util.*;

public class BookingRepository {

    // STATIC COUNTER -------------
    private static int totalBookingsCreated = 0;

    public static int getTotalBookingsCreated() {
        return totalBookingsCreated;
    }

    // SINGLE COLLECTION -------------
    // ONE map for ALL bookings (confirmed, checked-in, cancelled, refunded, etc.)
    private Map<Integer, Booking> allBookings = new HashMap<>();

    // OTHER COLLECTIONS
    private List<User> users = new ArrayList<>();
    private List<Accommodation> accommodations = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    // DATA VALIDATOR -------------
    private class DataValidator {

        public boolean isValidEmail(String email) {
            return email != null && email.contains("@") && email.contains(".");
        }

        public boolean isValidPrice(double price) {
            return price > 0;
        }

        public boolean isValidCapacity(int capacity) {
            return capacity >= 1;
        }

        public boolean isValidStars(int stars) {
            return stars >= 1 && stars <= 5;
        }

        public boolean areDatesValid(String checkIn, String checkOut) {
            if (checkIn == null || checkOut == null)
                return false;
            if (!checkIn.matches("\\d{4}-\\d{2}-\\d{2}"))
                return false;
            if (!checkOut.matches("\\d{4}-\\d{2}-\\d{2}"))
                return false;
            return checkIn.compareTo(checkOut) < 0;
        }

        public boolean isDuplicateBooking(int bookingId) {
            return allBookings.containsKey(bookingId);
        }

        public boolean isDuplicateUser(int userId) {
            for (User u : users)
                if (u.getUserId() == userId)
                    return true;
            return false;
        }

        public boolean isDuplicateAccommodation(int accId) {
            for (Accommodation a : accommodations)
                if (a.getAccId() == accId)
                    return true;
            return false;
        }
    }

    private DataValidator validator = new DataValidator();

    // USER MANAGEMENT-------------
    public void addUser(User user) {
        if (validator.isDuplicateUser(user.getUserId())) {
            System.out.println("  [ERROR] User ID " + user.getUserId() + " already exists!");
        } else {
            users.add(user);
            System.out.println("  [OK]    User added: " + user.getName());
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // ACCOMMODATION MANAGEMENT -------------
    public void addHotel(Hotel hotel) {
        if (validator.isDuplicateAccommodation(hotel.getAccId())) {
            System.out.println("  [ERROR] Accommodation ID " + hotel.getAccId() + " already exists!");
            return;
        }
        accommodations.add(hotel);
        System.out.println("  [OK]    " + hotel.getSummary());
    }

    public void addGuestHouse(GuestHouse gh) {
        if (validator.isDuplicateAccommodation(gh.getAccId())) {
            System.out.println("  [ERROR] Accommodation ID " + gh.getAccId() + " already exists!");
            return;
        }
        accommodations.add(gh);
        System.out.println("  [OK]    " + gh.getSummary());
    }

    public void addApartment(Apartment apt) {
        if (validator.isDuplicateAccommodation(apt.getAccId())) {
            System.out.println("  [ERROR] Accommodation ID " + apt.getAccId() + " already exists!");
            return;
        }
        accommodations.add(apt);
        System.out.println("  [OK]    " + apt.getSummary());
    }

    // BOOKING MANAGEMENT -------------

    public void addBooking(Booking booking) {
        if (booking == null || booking.getUser() == null || booking.getAccommodation() == null) {
            System.out.println("  [ERROR] Invalid booking data!");
            return;
        }
        if (!validator.areDatesValid(booking.getCheckInDate(), booking.getCheckOutDate())) {
            System.out.println("  [ERROR] Invalid dates for booking #" + booking.getBookingId());
            return;
        }
        if (validator.isDuplicateBooking(booking.getBookingId())) {
            System.out.println("  [ERROR] Booking ID " + booking.getBookingId() + " already exists!");
            return;
        }
        if (booking.getTotalPrice() <= 0) {
            System.out.println("  [ERROR] Total price is invalid for booking #" + booking.getBookingId());
            return;
        }

        if (!isAvailable(booking.getAccommodation().getAccId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate())) {
            System.out.println("  [ERROR] Booking #" + booking.getBookingId()
                    + " rejected - accommodation not available for these dates!");
            return;
        }

        allBookings.put(booking.getBookingId(), booking);
        totalBookingsCreated++;

        System.out.println("  [OK]    Booking #" + booking.getBookingId()
                + " | " + booking.getUser().getName()
                + " -> " + booking.getAccommodation().getName()
                + " | Check-in: " + booking.getCheckInDate()
                + " | Check-out: " + booking.getCheckOutDate()
                + " | " + booking.getNumberOfNights() + " nights"
                + " | $" + String.format("%.2f", booking.getTotalPrice())
                + " | Status: " + booking.getStatus()
                + " | Confirmed: " + booking.getConfirmedDate());
    }

    public void updateBookingStatus(int bookingId, BookingStatus newStatus) {
        Booking booking = allBookings.get(bookingId);
        if (booking == null) {
            System.out.println("  [ERROR] Booking #" + bookingId + " not found!");
            return;
        }
        BookingStatus oldStatus = booking.getStatus();
        booking.setStatus(newStatus);
        System.out.println("  [OK]    Booking #" + bookingId + " status: " + oldStatus + " -> " + newStatus);
    }

    // DELETE METHODS -------------

    // HARD DELETE - Completely remove from collection
    public void hardDeleteBooking(int bookingId) {
        Booking removed = allBookings.remove(bookingId);
        if (removed == null) {
            System.out.println("  [ERROR] Booking #" + bookingId + " not found!");
            return;
        }

        System.out.println("  [OK]    Booking #" + bookingId + " hard deleted (removed from system)");
        System.out.println("  [INFO]  totalBookingsCreated still = " + totalBookingsCreated);
        System.out.println("  [INFO]  allBookings.size() now = " + allBookings.size());
    }

    // BOOKING QUERIES -------------

    // 1. getAllBookings() - EVERYTHING (confirmed, checked-in, completed,
    // cancelled, refunded)
    public Collection<Booking> getAllBookings() {
        return allBookings.values();
    }

    // 2 & 3. getBookingsByStatus(status) - Only bookings with specific status
    public Collection<Booking> getBookingsByStatus(BookingStatus status) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : allBookings.values()) {
            if (b.getStatus() == status) {
                result.add(b);
            }
        }
        return result;
    }

    // 4. getUserHistory(userId) - User's complete history (ALL their bookings)
    public List<Booking> getUserHistory(int userId) {
        List<Booking> history = new ArrayList<>();
        for (Booking b : allBookings.values()) {
            if (b.getUser().getUserId() == userId) {
                history.add(b);
            }
        }
        // Sort by check-in date (most recent first)
        history.sort((b1, b2) -> b2.getCheckInDate().compareTo(b1.getCheckInDate()));
        return history;
    }

    // 5. isAvailable(accId, dates) - Check if room is free
    public boolean isAvailable(int accommodationId, String checkIn, String checkOut) {
        for (Booking b : allBookings.values()) {
            // Only check ACTIVE bookings (CONFIRMED and CHECKED_IN)
            if (b.getAccommodation().getAccId() == accommodationId) {
                if (b.getStatus() == BookingStatus.CONFIRMED ||
                        b.getStatus() == BookingStatus.CHECKED_IN) {

                    String existingIn = b.getCheckInDate();
                    String existingOut = b.getCheckOutDate();

                    // Check if date ranges overlap
                    // Overlap happens when: newIn < existingOut AND newOut > existingIn
                    if (checkIn.compareTo(existingOut) < 0 &&
                            checkOut.compareTo(existingIn) > 0) {
                        System.out.println("  [REJECTED] Overlaps with booking #" + b.getBookingId());
                        return false; // Overlap found - NOT available
                    }
                }
            }
        }
        return true;
    }

    // PAYMENT MANAGEMENT -------------
    public void addPayment(Payment payment) {
        if (payment == null || payment.getBooking() == null) {
            System.out.println("  [ERROR] Payment must be linked to a valid booking!");
            return;
        }
        payments.add(payment);
        System.out.println("  [OK]    Payment #" + payment.getPaymentId()
                + " | Booking #" + payment.getBooking().getBookingId()
                + " | $" + String.format("%.2f", payment.getAmount())
                + " | " + payment.getMethod()
                + " | Payment Date: " + java.time.LocalDateTime.now().toLocalDate());
    }

    // DISPLAY METHODS -------------
    public void displayAllData() {
        System.out.println("\n" + "-".repeat(90));
        System.out.println("SYSTEM DATA OVERVIEW");
        System.out.println("-".repeat(90));

        // USERS
        System.out.println("\n[USERS] Total: " + users.size());
        System.out.println("-".repeat(70));
        for (User u : users) {
            System.out.println("  ID:" + u.getUserId() + " | " + u.getName());
        }

        // ACCOMMODATIONS
        System.out.println("\n[ACCOMMODATIONS] Total: " + accommodations.size());
        System.out.println("-".repeat(70));
        for (Accommodation a : accommodations) {
            System.out.println("  ID:" + a.getAccId() + " | " + a.getSummary());
        }

        // 1. getAllBookings() - EVERYTHING (complete history)
        System.out.println("\n[1. getAllBookings() - COMPLETE HISTORY] Total: " + allBookings.size());
        System.out.println("-".repeat(70));
        for (Booking b : getAllBookings()) {
            String icon;
            switch (b.getStatus()) {
                case CONFIRMED:
                    icon = "[CONFIRMED]";
                    break;
                case CHECKED_IN:
                    icon = "[IN]";
                    break;
                case CHECKED_OUT:
                    icon = "[OUT]";
                    break;
                case CANCELLED:
                    icon = "[CANCELLED]";
                    break;
                case REFUNDED:
                    icon = "[REFUNDED]";
                    break;
                default:
                    icon = "[ ]";
            }
            System.out.println("  " + icon + " #" + b.getBookingId()
                    + " | " + b.getUser().getName()
                    + " | " + b.getAccommodation().getName()
                    + " | " + b.getCheckInDate() + " to " + b.getCheckOutDate()
                    + " | " + b.getStatus()
                    + " | $" + String.format("%.2f", b.getTotalPrice()));
        }

        // 2 & 3. getBookingsByStatus() examples
        System.out.println("\n[2. getBookingsByStatus(CONFIRMED) - Upcoming Stays] Total: "
                + getBookingsByStatus(BookingStatus.CONFIRMED).size());
        System.out.println("-".repeat(70));
        for (Booking b : getBookingsByStatus(BookingStatus.CONFIRMED)) {
            System.out.println(
                    "   #" + b.getBookingId() + " | " + b.getUser().getName() + " | " + b.getAccommodation().getName());
        }

        System.out.println("\n[3. getBookingsByStatus(CHECKED_IN) - Current Guests] Total: "
                + getBookingsByStatus(BookingStatus.CHECKED_IN).size());
        System.out.println("-".repeat(70));
        for (Booking b : getBookingsByStatus(BookingStatus.CHECKED_IN)) {
            System.out.println("    #" + b.getBookingId() + " | " + b.getUser().getName() + " | "
                    + b.getAccommodation().getName());
        }

        // 4. getUserHistory() examples
        System.out.println("\n[4. getUserHistory() - Complete User History]");
        System.out.println("-".repeat(70));
        for (User u : users) {
            System.out.println("\n  User: " + u.getName() + " (ID: " + u.getUserId() + ")");
            List<Booking> history = getUserHistory(u.getUserId());
            if (history.isEmpty()) {
                System.out.println("    No bookings found.");
            } else {
                for (Booking b : history) {
                    System.out.println("    #" + b.getBookingId() + " | " + b.getAccommodation().getName()
                            + " | " + b.getCheckInDate() + " to " + b.getCheckOutDate()
                            + " | " + b.getStatus());
                }
            }
        }

        // 5. isAvailable() demo
        System.out.println("\n[5. isAvailable() - Availability Check Demo]");
        System.out.println("-".repeat(70));
        if (accommodations.size() > 0) {
            int testId = accommodations.get(0).getAccId();
            String testDates = "2026-12-01 to 2026-12-05";
            boolean avail = isAvailable(testId, "2026-12-01", "2026-12-05");
            System.out.println(
                    "  Accommodation ID " + testId + " available on " + testDates + "? " + (avail ? "YES" : "NO"));
        }

        // PAYMENTS
        System.out.println("\n[PAYMENTS] Total: " + payments.size());
        System.out.println("-".repeat(70));
        for (Payment p : payments) {
            System.out.println("  Payment #" + p.getPaymentId()
                    + " | Booking #" + p.getBooking().getBookingId()
                    + " | $" + String.format("%.2f", p.getAmount())
                    + " | " + p.getMethod());
        }

        // STATIC COUNTER
        System.out.println("\n[STATIC COUNTER]");
        System.out.println("-".repeat(70));
        System.out.println(
                "  BookingRepository.getTotalBookingsCreated() = " + BookingRepository.getTotalBookingsCreated());
        System.out.println("  allBookings.size() (currently stored)       = " + allBookings.size());
        System.out.println("  -> Cancelled/Refunded bookings stay in allBookings for history!");
    }
}