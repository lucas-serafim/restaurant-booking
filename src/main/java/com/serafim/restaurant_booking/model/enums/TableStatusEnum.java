package com.serafim.restaurant_booking.model.enums;

public enum TableStatusEnum {
    AVAILABLE("available"),
    INACTIVE("inactive");

    private String role;

    TableStatusEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
