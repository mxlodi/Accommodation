import models.User;
import models.Hotel;
import models.GuestHouse;
import models.Apartment;
import models.Booking;
import models.BookingStatus;
import models.Payment;
import models.Accommodation;
import repository.BookingRepository;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BookingRepository repo = new BookingRepository();

        // 1. SETUP: Initialize system with users and rooms
        setupInitialData(repo);

        System.out.println("\n" + "=".repeat(70));

        // 2. SCENARIO A: First user searches for dates
        String checkIn = "2026-10-01";
        String checkOut = "2026-10-05";

        System.out.println("\n[USER A] Searching for rooms between " + checkIn + " and " + checkOut + "...");
        browseAvailableRooms(repo, checkIn, checkOut);

        // 3. ACTION: User A books "Hotel Plaza" (ID 10)
        System.out.println("\nUser A (Teddy) is booking Hotel Plaza...");

        User teddy = repo.findUserById(1);
        Accommodation plaza = repo.findAccommodationById(10);
        Booking bookingA = new Booking(1001, teddy, plaza, checkIn, checkOut, BookingStatus.CONFIRMED);
        repo.addBooking(bookingA);

        // 4. SCENARIO B: Second user searches for SAME dates
        System.out.println("\n" + "-".repeat(70));
        System.out.println("[USER B] Searching for same dates: " + checkIn + " to " + checkOut);
        System.out.println("EXPECTED: Hotel Plaza should be HIDDEN.");
        browseAvailableRooms(repo, checkIn, checkOut);

        // 5. SEARCHABLE INTERFACE DEMO
        printHeader("SEARCHABLE INTERFACE DEMO");
        System.out.println("Searching bookings for 'Teddy'...");
        repo.searchBookingsByUserName("Teddy").forEach(b -> b.display());

        // 6. INVENTORY COUNTS
        printHeader("SYSTEM INVENTORY");
        System.out.println("Total Hotels: " + repo.getHotelCount());
        System.out.println("Total Guest Houses: " + repo.getGuestHouseCount());
        System.out.println("Total Apartments: " + repo.getApartmentCount());
        System.out.println("=> Total Bookings: " + repo.getTotalBookingsCount());

        // 7. PAYMENT PROCESSING DEMO
        printHeader("PAYMENT PROCESSING DEMO");
        
        System.out.println("\nCreating payment for Booking #1001...");
        Payment payment = new Payment(5001, bookingA, "CARD");
        repo.addPayment(payment);
        
        payment.display();
        
        System.out.println("\nAll payments in system:");
        for (Payment p : repo.getAllPayments()) {
            p.displayName();
        }
    }

    // Only displays rooms that are actually available so that we can hide unavailable rooms
    private static void browseAvailableRooms(BookingRepository repo, String start, String end) {
        System.out.println("\nAVAILABLE ACCOMMODATIONS FOUND:");

        List<Accommodation> available = repo.getAvailableRooms(start, end);

        if (available.isEmpty()) {
            System.out.println("  (!) No rooms available for these dates.");
        } else {
            for (Accommodation acc : available) {
                acc.display();
                System.out.println("-----------------------------------");
            }
        }
    }

    private static void setupInitialData(BookingRepository repo) {
        System.out.println("\n--- SYSTEM SETUP ---");

        // Create Users
        System.out.println("\nUSERS:");
        repo.addUser(new User(1, "Teddy Bear", "teddy@email.com", "012345678"));
        repo.addUser(new User(2, "Winnie Pooh", "winnie@disney.com", "098765432"));

        // Create different types of rooms
        System.out.println("\nACCOMMODATIONS:");
        repo.addHotel(new Hotel(10, "Hotel Plaza", 200.0, 2, 5, true, true));
        repo.addApartment(new Apartment(20, "City Center Loft", 120.0, 4, 15, true, true));
        repo.addGuestHouse(new GuestHouse(30, "Quiet Cottage", 80.0, 2, true, false));
    }

    private static void printHeader(String title) {
        System.out.println("\n\n[" + title + "]");
        System.out.println("-".repeat(title.length() + 2));
    }
}