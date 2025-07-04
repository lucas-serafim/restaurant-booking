package com.serafim.restaurant_booking.model.domain.exception;

import java.io.IOException;

public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(String message) {
        super(message);
    }
}
