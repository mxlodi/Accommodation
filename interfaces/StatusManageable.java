package interfaces;

import models.BookingStatus;

public interface StatusManageable {
    BookingStatus getStatus();
    void setStatus(BookingStatus newStatus);
    boolean canChangeTo(BookingStatus nextStatus);
}