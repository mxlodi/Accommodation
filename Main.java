public class Main {
    public static void main(String[] args) {
        // Create a User
        User user1 = new User();
        user1.userId = 1;
        user1.name = "Johnny";
        user1.email = "john@email.com";
        user1.phone = "012-345-678";
        
        // Create a Hotel
        Hotel hotel1 = new Hotel();
        hotel1.hotelId = 101;
        hotel1.name = "Grand Plaza";
        hotel1.pricePerNight = 150.50;
        hotel1.capacity = 2;
        hotel1.stars = 5;
        
        // Create a Booking
        Booking booking1 = new Booking();
        booking1.bookingId = 1001;
        booking1.userId = user1.userId;
        booking1.accommodationId = hotel1.hotelId;
        booking1.checkInDate = "2026-04-10";
        booking1.checkOutDate = "2026-04-15";
        booking1.totalPrice = 750.00;
        booking1.status = "CONFIRMED";
        
        // Print to show it works
        System.out.println("User: " + user1.name);
        System.out.println("Hotel: " + hotel1.name);
        System.out.println("Booking total: $" + booking1.totalPrice);
    }
}