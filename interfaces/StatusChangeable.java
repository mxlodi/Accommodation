// package interfaces;

// import models.BookingStatus;

// public interface StatusChangeable {
//     BookingStatus getStatus();

//     void setStatus(BookingStatus status);

//     boolean canChangeTo(BookingStatus newStatus);
// }

package interfaces;

import models.BookingStatus;

public interface StatusChangeable {
    BookingStatus getStatus();
    void setStatus(BookingStatus status);
    boolean canChangeTo(BookingStatus newStatus);
}