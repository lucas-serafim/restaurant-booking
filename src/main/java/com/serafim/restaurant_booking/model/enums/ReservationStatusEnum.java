package com.serafim.restaurant_booking.model.enums;

public enum ReservationStatusEnum {
    ACTIVE("active"),
    CANCELLED("cancelled");

    private String role;

    ReservationStatusEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
