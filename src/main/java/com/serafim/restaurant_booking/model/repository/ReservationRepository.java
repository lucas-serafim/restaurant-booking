package com.serafim.restaurant_booking.model.repository;

import com.serafim.restaurant_booking.model.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findAllByUserId(UUID id);

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Reservation r
        WHERE r.restaurantTable.id = :tableId
          AND r.bookedDate < :endTime
          AND r.bookedDate >= :startTimeMinus1Hour
    """)
    boolean existsReservationInPeriod(UUID tableId, Instant startTimeMinus1Hour, Instant endTime);
}
