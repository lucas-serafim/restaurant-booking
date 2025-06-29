package com.serafim.restaurant_booking.model.domain.table;

import com.serafim.restaurant_booking.model.enums.TableStatusEnum;

import java.util.UUID;

public record RestaurantTableResponseDTO(
        UUID id,
        String name,
        Integer capacity,
        TableStatusEnum status
) {
}
