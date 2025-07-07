package com.serafim.restaurant_booking.model.domain.user;

import com.serafim.restaurant_booking.model.enums.UserRoleEnum;

public record UserRequestDTO(
        String name,
        String email,
        String password
) {
}
