package models;

public interface StatusChangeable {
    BookingStatus getStatus();

    void setStatus(BookingStatus status);

    boolean canChangeTo(BookingStatus newStatus);
}
