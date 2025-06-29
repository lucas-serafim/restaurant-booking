package com.serafim.restaurant_booking.model.domain.User;

import com.serafim.restaurant_booking.model.enums.UserRoleEnum;

public record UserRequestDTO(
        String name,
        String email,
        String password,
        UserRoleEnum role
) {
}
