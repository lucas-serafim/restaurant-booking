package com.serafim.restaurant_booking.model.domain.table;

import com.serafim.restaurant_booking.model.enums.TableStatusEnum;

public record RestaurantTableRequestDTO(
        String name,
        Integer capacity,
        TableStatusEnum status
) {
}
