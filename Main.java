import models.User;
import models.Hotel;
import models.GuestHouse;
import models.Apartment;
import models.Booking;
import models.BookingStatus;
import models.Payment;
import repository.BookingRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("~".repeat(  90));
        System.out.println("  WEEK 4: ACCOMMODATION BOOKING SYSTEM");
        System.out.println("  Object Relationships | Association | Composition");
        System.out.println("~".repeat(90));

        BookingRepository repo = new BookingRepository();

        // CREATE USERS -------------
        System.out.println("\nSTEP 1: CREATING USERS");
        System.out.println("-".repeat(90));

        User user1 = new User(1, "Teddy Bear", "teddy@email.com", "012345678");
        User user2 = new User(2, "Winnie Pooh", "winnie@disney.com", "098765432");
        User user3 = new User(3, "Mickey Mouse", "mickey@disney.com", "011223344");

        repo.addUser(user1);
        repo.addUser(user2);
        repo.addUser(user3);

        // CREATE ACCOMMODATIONS -------------
        System.out.println("\nSTEP 2: CREATING ACCOMMODATIONS");
        System.out.println("-".repeat(90));

        Hotel hotel1 = new Hotel(101, "Grand Plaza Hotel", 250.00, 2, 5, true, true);
        Hotel hotel2 = new Hotel(102, "Sunset Beach Resort", 180.00, 4, 4, true, false);
        GuestHouse gh1 = new GuestHouse(201, "Cozy Corner Cottage", 85.00, 2, true, false);
        Apartment apt1 = new Apartment(301, "Skyline Luxury Loft", 200.00, 4, 15, true, false);

        repo.addHotel(hotel1);
        repo.addHotel(hotel2);
        repo.addGuestHouse(gh1);
        repo.addApartment(apt1);

        // CREATE BOOKINGS -------------
        System.out.println("\nSTEP 3: CREATING BOOKINGS");
        System.out.println("-".repeat(90));

        // Booking 1: Teddy books Grand Plaza Hotel for 5 nights
        Booking booking1 = new Booking(1001, user1, hotel1,
                "2026-05-10", "2026-05-15", BookingStatus.CONFIRMED);
        repo.addBooking(booking1);

        // Booking 2: Winnie books Cozy Corner for 4 nights
        Booking booking2 = new Booking(1002, user2, gh1,
                "2026-06-01", "2026-06-05", BookingStatus.CONFIRMED);
        repo.addBooking(booking2);

        // Booking 3: Mickey books Skyline Loft for 7 nights
        Booking booking3 = new Booking(1003, user3, apt1,
                "2026-07-01", "2026-07-08", BookingStatus.CONFIRMED);
        repo.addBooking(booking3);

        // TEST AVAILABILITY CHECK -------------
        System.out.println("\nSTEP 4: TESTING AVAILABILITY CHECK");
        System.out.println("-".repeat(90));

        boolean available = repo.isAvailable(101, "2026-05-12", "2026-05-14");
        System.out
                .println("  Is Grand Plaza Hotel available May 12-14? " + (available ? "YES" : "NO (already booked)"));

        available = repo.isAvailable(101, "2026-05-20", "2026-05-25");
        System.out.println("  Is Grand Plaza Hotel available May 20-25? " + (available ? "YES" : "NO"));

        // Try to book overlapping dates (REJECTED!)
        System.out.println("\n  Attempting double-booking (should be rejected):");
        Booking overlapping = new Booking(1004, user2, hotel1,
                "2026-05-12", "2026-05-14", BookingStatus.CONFIRMED);
        repo.addBooking(overlapping); // failed

        // UPDATE BOOKING STATUS -------------
        System.out.println("\nSTEP 5: UPDATING BOOKING STATUSES");
        System.out.println("-".repeat(90));

        repo.updateBookingStatus(1001, BookingStatus.CHECKED_IN);
        repo.updateBookingStatus(1002, BookingStatus.CANCELLED);
        repo.updateBookingStatus(1003, BookingStatus.CHECKED_OUT);

        // CREATE PAYMENTS -------------
        System.out.println("\nSTEP 6: CREATING PAYMENTS");
        System.out.println("-".repeat(90));

        Payment pay1 = new Payment(5001, booking1, "CARD");
        Payment pay2 = new Payment(5002, booking3, "ONLINE");

        repo.addPayment(pay1);
        repo.addPayment(pay2);

        // DEMO USER HISTORY -------------
        System.out.println("\nSTEP 7: USER HISTORY DEMO...");
        System.out.println("-".repeat(90));

        System.out.println("\n  Teddy Bear's Complete Booking History:");
        for (Booking b : repo.getUserHistory(1)) {
            System.out.println("    #" + b.getBookingId()
                    + " | " + b.getAccommodation().getName()
                    + " | " + b.getCheckInDate() + " to " + b.getCheckOutDate()
                    + " | " + b.getStatus()
                    + " | $" + String.format("%.2f", b.getTotalPrice()));
        }

        // DEMO DELETE METHODS -------------
        System.out.println("\nSTEP 8: HARD DELETE DEMO...");
        System.out.println("-".repeat(90));

        System.out.println("\n  BEFORE any delete:");
        System.out.println("    totalBookingsCreated = " + BookingRepository.getTotalBookingsCreated());
        System.out.println("    allBookings.size() = " + repo.getAllBookings().size());

        // HARD DELETE - completely removed -------------
        System.out.println("\n  HARD DELETE booking #1003:");
        repo.hardDeleteBooking(1003);

        System.out.println("\n  AFTER hard delete:");
        System.out.println(
                "    totalBookingsCreated = " + BookingRepository.getTotalBookingsCreated() + " (same - remembers!)");
        System.out.println("    allBookings.size() = " + repo.getAllBookings().size() + " (DECREASED!)");

        // Show what's left
        System.out.println("\n  Remaining bookings:");
        for (Booking b : repo.getAllBookings()) {
            System.out.println("    #" + b.getBookingId() + " | " + b.getUser().getName()
                    + " | " + b.getAccommodation().getName()
                    + " | Status: " + b.getStatus());
        }

        // DISPLAY ALL DATA -------------
        repo.displayAllData();

        // STATIC USAGE DEMO -------------
        System.out.println("\n" + "~".repeat(90));
        System.out.println("STATIC USAGE DEMO");
        System.out.println("~".repeat(90));

        System.out.println(
                "\n  BookingRepository.getTotalBookingsCreated() = " + BookingRepository.getTotalBookingsCreated());
        System.out.println("  This counts ALL bookings EVER created (including cancelled)");
        System.out.println("  Cancelled bookings are preserved in allBookings for history!");
    }
}


