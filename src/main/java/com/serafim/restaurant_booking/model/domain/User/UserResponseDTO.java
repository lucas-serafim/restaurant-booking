package com.serafim.restaurant_booking.model.domain.User;

import com.serafim.restaurant_booking.model.enums.UserRoleEnum;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        UserRoleEnum role
) {
}
