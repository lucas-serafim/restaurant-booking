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
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantTableService tableService;

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationResponseDTO create(UUID userId, UUID tableId, ReservationRequestDTO dto) {
        Instant now = Instant.now();

        if (dto.bookedDate().isBefore(now)) {
            throw new IllegalArgumentException("Booked date must be a future date.");
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            throw new NotFoundException("User not found. Id: " + userId);
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

        table.setStatus(TableStatusEnum.BOOKED);
        Reservation reservation = new Reservation(user, table, dto.bookedDate(), ReservationStatusEnum.ACTIVE);

        tableService.update(table);
        reservationRepository.save(reservation);

        return new ReservationResponseDTO(
                reservation.getId(),
                userId,
                tableId,
                reservation.getBookedDate(),
                reservation.getStatus()
        );
    }
}
