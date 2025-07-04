package com.serafim.restaurant_booking.model.repository;

import com.serafim.restaurant_booking.model.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
