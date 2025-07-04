package com.serafim.restaurant_booking.model.domain.reservation;

import java.time.Instant;
import java.util.UUID;

public record ReservationRequestDTO(
        Integer quantityCustomers,
        Instant bookedDate
) {
}
