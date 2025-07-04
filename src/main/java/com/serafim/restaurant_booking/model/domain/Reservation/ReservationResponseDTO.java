package com.serafim.restaurant_booking.model.domain.reservation;

import com.serafim.restaurant_booking.model.domain.table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.user.User;
import com.serafim.restaurant_booking.model.enums.ReservationStatusEnum;

import java.time.Instant;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        UUID userId,
        UUID tableId,
        Instant bookedDate,
        ReservationStatusEnum status
) {
}
