package com.serafim.restaurant_booking.model.domain.exception;

public class RestaurantTableException extends RuntimeException {
    private String message;

    public RestaurantTableException(String message) {
        super(message);
    }
}
