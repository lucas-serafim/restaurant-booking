package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.reservation.ReservationRequestDTO;
import com.serafim.restaurant_booking.model.domain.reservation.ReservationResponseDTO;
import com.serafim.restaurant_booking.model.domain.user.User;
import com.serafim.restaurant_booking.model.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/restaurant-tables/{tableId}")
    public ReservationResponseDTO create(
            @PathVariable UUID tableId,
            @RequestBody ReservationRequestDTO body,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return this.reservationService.create(user, tableId, body);
    }

    @GetMapping()
    public List<ReservationResponseDTO> getReservationsByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return this.reservationService.getReservationsByUserId(user.getId());
    }
}
