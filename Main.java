import models.Booking;
import models.BookingStatus;
import models.Hotel;
import models.User;
import models.GuestHouse;
import models.Apartment;
import repository.BookingRepository;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     WEEK 3: ACCOMMODATION BOOKING SYSTEM                   ║");
        System.out.println("║     Private Inner Class | Object Storage | If-Else         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        BookingRepository repo = new BookingRepository();
        
        // ========== 1. CREATE USERS ==========
        System.out.println("📝 STEP 1: Creating Users...");
        System.out.println("-".repeat(40));
        
        User user1 = new User(1, "Teddy Bear", "teddy@email.com", "012345678");
        User user2 = new User(2, "Winnie Pooh", "winnie@disney.com", "098765432");
        User user3 = new User(3, "Mickey Mouse", "mickey@disney.com", "011223344");
        User user4 = new User(4, "", "invalid-email", "");
        
        repo.addUser(user1);
        repo.addUser(user2);
        repo.addUser(user3);
        repo.addUser(user4);
        
        // ========== 2. CREATE HOTELS ==========
        System.out.println("\n🏨 STEP 2: Creating Hotels...");
        System.out.println("-".repeat(40));
        
        Hotel hotel1 = new Hotel(101, "Grand Plaza Hotel", 250.00, 2, 5, true, true);
        Hotel hotel2 = new Hotel(102, "Sunset Beach Resort", 180.00, 4, 4, true, false);
        Hotel hotel3 = new Hotel(103, "Budget Inn", 75.00, 2, 2, false, false);
        Hotel hotel4 = new Hotel(104, "Invalid Hotel", -50.00, 0, 6, false, false);
        
        repo.addHotel(hotel1);
        repo.addHotel(hotel2);
        repo.addHotel(hotel3);
        repo.addHotel(hotel4);
        
        // ========== 3. CREATE GUEST HOUSES ==========
        System.out.println("\n🏠 STEP 3: Creating Guest Houses...");
        System.out.println("-".repeat(40));
        
        GuestHouse gh1 = new GuestHouse(201, "Cozy Corner Cottage", 85.00, 2, true, false);
        GuestHouse gh2 = new GuestHouse(202, "Mountain View Lodge", 120.00, 4, true, true);
        GuestHouse gh3 = new GuestHouse(203, "Garden Retreat", 65.00, 3, false, false);
        
        repo.addGuestHouse(gh1);
        repo.addGuestHouse(gh2);
        repo.addGuestHouse(gh3);
        
        // ========== 4. CREATE APARTMENTS ==========
        System.out.println("\n🏢 STEP 4: Creating Apartments...");
        System.out.println("-".repeat(40));
        
        Apartment apt1 = new Apartment(301, "Skyline Luxury Loft", 200.00, 4, 15, true, false);
        Apartment apt2 = new Apartment(302, "Downtown Studio", 150.00, 2, 5, true, true);
        Apartment apt3 = new Apartment(303, "Garden View Apartment", 120.00, 3, 2, false, true);
        
        repo.addApartment(apt1);
        repo.addApartment(apt2);
        repo.addApartment(apt3);
        
        // ========== 5. CREATE BOOKINGS ==========
        System.out.println("\n📅 STEP 5: Creating Bookings...");
        System.out.println("-".repeat(40));
        
        Booking booking1 = new Booking(1001, user1, "2026-05-10", "2026-05-15", 1250.00, BookingStatus.CONFIRMED);
        Booking booking2 = new Booking(1002, user2, "2026-06-01", "2026-06-05", 720.00, BookingStatus.CONFIRMED);
        Booking booking3 = new Booking(1003, user3, "2026-07-20", "2026-07-15", 500.00, BookingStatus.CONFIRMED);
        Booking booking4 = new Booking(1001, user1, "2026-08-01", "2026-08-05", 400.00, BookingStatus.CONFIRMED);
        
        repo.addBooking(booking1);
        repo.addBooking(booking2);
        repo.addBooking(booking3);
        repo.addBooking(booking4);
        
        // ========== 6. UPDATE BOOKING STATUS ==========
        System.out.println("\n🔄 STEP 6: Updating Booking Status...");
        System.out.println("-".repeat(40));
        
        repo.updateBookingStatus(1001, BookingStatus.CHECKED_IN);
        repo.updateBookingStatus(1002, BookingStatus.CANCELLED);
        repo.updateBookingStatus(9999, BookingStatus.CONFIRMED);
        
        // ========== 7. DISPLAY ALL DATA ==========
        repo.displayAllData();
        
        // ========== 8. WEEK 3 SUMMARY ==========
        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ WEEK 3 OBJECTIVES COMPLETED");
        System.out.println("=".repeat(60));
        System.out.println("\n📌 Requirements Met:");
        System.out.println("   1. PACKAGE NAMES (lowercase): 'models' and 'repository'");
        System.out.println("   2. PRIVATE INNER CLASS: DataValidator inside BookingRepository");
        System.out.println("   3. IF-ELSE CONDITIONS: Used in validation, duplicate checks");
        System.out.println("   4. OBJECT STORAGE: ArrayLists storing all entities");
        System.out.println("\n🎉 Week 3 implementation complete!");
    }
}