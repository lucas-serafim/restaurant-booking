package com.serafim.restaurant_booking.model.service;

import com.serafim.restaurant_booking.model.domain.exception.NotFoundException;
import com.serafim.restaurant_booking.model.domain.exception.RestaurantTableException;
import com.serafim.restaurant_booking.model.domain.reservation.Reservation;
import com.serafim.restaurant_booking.model.domain.reservation.ReservationRequestDTO;
import com.serafim.restaurant_booking.model.domain.reservation.ReservationResponseDTO;
import com.serafim.restaurant_booking.model.domain.table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.user.User;
import com.serafim.restaurant_booking.model.enums.ReservationStatusEnum;
import com.serafim.restaurant_booking.model.enums.TableStatusEnum;
import com.serafim.restaurant_booking.model.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantTableService tableService;

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationResponseDTO create(User user, UUID tableId, ReservationRequestDTO dto) {
        Instant now = Instant.now();

        if (dto.bookedDate().isBefore(now)) {
            throw new IllegalArgumentException("Booked date must be a future date.");
        }

        RestaurantTable table = this.tableService.findById(tableId);

        if (table == null) {
            throw new NotFoundException("Table not found. Id: " + tableId);
        }

        if (table.getStatus() != TableStatusEnum.AVAILABLE) {
            throw new RestaurantTableException("Table is not available.");
        }

        if (table.getCapacity() < dto.quantityCustomers()) {
            throw new RestaurantTableException("Table capacity out of bounds.");
        }

        Instant startTime = dto.bookedDate();
        Instant endTime = startTime.plus(1, ChronoUnit.HOURS);
        Instant startTimeMinus1Hour = startTime.minus(1, ChronoUnit.HOURS);

        boolean isAlreadyBooked = reservationRepository.existsReservationInPeriod(tableId, startTimeMinus1Hour, endTime);
        System.out.println("isAlreadyBooked: " + isAlreadyBooked);

        if (isAlreadyBooked) {
            throw new RestaurantTableException("Table is already booked for this date time.");
        }

        Reservation reservation = new Reservation(user, table, dto.bookedDate(), ReservationStatusEnum.ACTIVE);

        reservationRepository.save(reservation);

        return new ReservationResponseDTO(
                reservation.getId(),
                user.getId(),
                tableId,
                reservation.getBookedDate(),
                reservation.getStatus()
        );
    }

    public List<ReservationResponseDTO> getReservations(UUID userId) {
        List<Reservation> reservationList = this.reservationRepository.findAllByUserId(userId);
        return reservationList.stream().map(this::toResponseDTO).toList();
    }

    public void cancel (UUID reservationId, User user) {
        Reservation reservation = this.findById(reservationId);

        if (reservation == null || reservation.getUser().getId() != user.getId()) {
            throw new NotFoundException("Reservation not found. Id: " + reservationId);
        }

        reservation.setStatus(ReservationStatusEnum.CANCELLED);

        RestaurantTable restaurantTable = reservation.getRestaurantTable();
        restaurantTable.setStatus(TableStatusEnum.AVAILABLE);

        this.reservationRepository.save(reservation);
        this.tableService.update(restaurantTable);
    }

    public Reservation findById(UUID reservationId) {
        Optional<Reservation> reservation = this.reservationRepository.findById(reservationId);
        return reservation.orElse(null);
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getRestaurantTable().getId(),
                reservation.getBookedDate(),
                reservation.getStatus()
        );
    }
}
