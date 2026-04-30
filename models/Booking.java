package models;

// Booking connects BOTH User and Accommodation
// totalPrice is CALCULATED from accommodation.pricePerNight × numberOfNights (not entered manually)

public class Booking {

    private int bookingId;
    private User user;
    // Association: Booking knows its User
    private Accommodation accommodation;
    // Association: Booking knows which place was booked
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;
    private BookingStatus status;
    private String confirmedDate;

    public Booking(int bookingId, User user, Accommodation accommodation,
            String checkInDate, String checkOutDate, BookingStatus status) {
        this.bookingId = bookingId;
        this.user = user;
        this.accommodation = accommodation;
        this.confirmedDate = java.time.LocalDate.now().toString();

        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        setStatus(status);
        this.totalPrice = calculateTotalPrice();
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public String getConfirmedDate() {
        return confirmedDate;
    }

    // Keep getUserId() for backward-compatibility with BookingRepository display
    public User getUserId() {
        return user;
    }

    // ── Setters ───────────────────────────────────────────────────────────────
    public void setCheckInDate(String checkInDate) {
        if (checkInDate == null || checkInDate.isEmpty()) {
            System.out.println("  [WARNING] Check-in date empty. Using default 2026-01-01.");
            this.checkInDate = "2026-01-01";
        } else if (!isValidDateFormat(checkInDate)) {
            System.out.println("  [WARNING] Invalid date format '" + checkInDate + "'. Using default 2026-01-01.");
            this.checkInDate = "2026-01-01";
        } else {
            this.checkInDate = checkInDate;
        }
    }

    public void setCheckOutDate(String checkOutDate) {
        if (checkOutDate == null || checkOutDate.isEmpty()) {
            System.out.println("  [WARNING] Check-out date empty. Using default 2026-01-02.");
            this.checkOutDate = "2026-01-02";
        } else if (!isValidDateFormat(checkOutDate)) {
            System.out.println("  [WARNING] Invalid date format '" + checkOutDate + "'. Using default 2026-01-02.");
            this.checkOutDate = "2026-01-02";
        } else {
            this.checkOutDate = checkOutDate;
        }
    }

    public void setStatus(BookingStatus status) {
        if (status == null) {
            System.out.println("  [WARNING] Status cannot be null. Setting to CANCELLED.");
            this.status = BookingStatus.CANCELLED;
        } else {
            this.status = status;
        }
    }

    // ── BUSINESS LOGIC ────────────────────────────────────────────────────────

    // Calculate number of nights between checkIn and checkOut (YYYY-MM-DD strings)
    public int getNumberOfNights() {
        if (checkInDate == null || checkOutDate == null)
            return 0;
        try {
            // Parse YYYY-MM-DD without importing LocalDate to stay simple
            String[] in = checkInDate.split("-");
            String[] out = checkOutDate.split("-");
            int inDays = Integer.parseInt(in[0]) * 365 + Integer.parseInt(in[1]) * 30 + Integer.parseInt(in[2]);
            int outDays = Integer.parseInt(out[0]) * 365 + Integer.parseInt(out[1]) * 30 + Integer.parseInt(out[2]);
            return Math.max(0, outDays - inDays);
        } catch (Exception e) {
            return 0;
        }
    }

    // Calculates price
    private double calculateTotalPrice() {
        if (accommodation == null)
            return 0.0;
        return accommodation.getPricePerNight() * getNumberOfNights();
    }

    // Date validation: must match YYYY-MM-DD and have valid ranges
    private boolean isValidDateFormat(String date) {
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}"))
            return false;
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return month >= 1 && month <= 12 && day >= 1 && day <= 31;
    }

}