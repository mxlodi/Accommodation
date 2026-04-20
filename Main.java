public class Main {
    public static void main(String[] args) {
        
        // Create User
        User user1 = new User(1, "Teddy", "teddy@email.com", "012-345-678");
        
        // Create Hotel (7 parameters: id, name, price, capacity, stars, hasBreakfast, hasPool)
        Hotel hotel1 = new Hotel(101, "Grand Plaza", 150.50, 2, 5, true, true);
        
        // Create Booking (6 parameters: id, User object, checkIn, checkOut, total, status)
        Booking booking1 = new Booking(
            1001,
            user1,                    
            "2026-04-10",
            "2026-04-15",
            750.00,
            BookingStatus.CONFIRMED
        );
        
        // Display
        System.out.println("- BOOKING CONFIRMATION -");
        System.out.println("Booking ID: " + booking1.getBookingId());
        System.out.println("Guest: " + user1.getName());
        System.out.println("Property: " + hotel1.getName());
        System.out.println("Check-in: " + booking1.getCheckInDate());
        System.out.println("Check-out: " + booking1.getCheckOutDate());
        System.out.println("Total: $" + booking1.getTotalPrice());
        System.out.println("Status: " + booking1.getStatus());
        
        // Update status
        System.out.println("\n--- Updating Booking ---");
        booking1.setStatus(BookingStatus.CHECKED_IN);
        System.out.println("New status: " + booking1.getStatus());
        
        // Validation demo
        System.out.println("\n--- Validation Demo ---");
        hotel1.setPricePerNight(-100.00);
        System.out.println("Price still: $" + hotel1.getPricePerNight());
        
        hotel1.setPricePerNight(200.00);
        System.out.println("New price: $" + hotel1.getPricePerNight());
        
        hotel1.setStars(6);
        System.out.println("Stars still: " + hotel1.getStars());
    }
}