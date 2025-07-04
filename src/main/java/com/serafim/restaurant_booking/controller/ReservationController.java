package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.reservation.ReservationRequestDTO;
import com.serafim.restaurant_booking.model.domain.reservation.ReservationResponseDTO;
import com.serafim.restaurant_booking.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/users/{userId}/restaurant-tables/{tableId}")
    public ReservationResponseDTO create(
            @PathVariable UUID userId,
            @PathVariable UUID tableId,
            @RequestBody ReservationRequestDTO body
    ) {
        return this.reservationService.create(userId, tableId, body);
    }
}
