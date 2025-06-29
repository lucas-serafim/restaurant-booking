package com.serafim.restaurant_booking.model.domain.authorization;

public record AuthenticationDTO(
        String email,
        String password
) {
}
