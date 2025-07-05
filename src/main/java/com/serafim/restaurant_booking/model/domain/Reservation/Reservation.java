package com.serafim.restaurant_booking.model.domain.reservation;

import com.serafim.restaurant_booking.model.domain.table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.user.User;
import com.serafim.restaurant_booking.model.enums.ReservationStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable restaurantTable;

    @Column(name = "booked_date")
    private Instant bookedDate;

    private ReservationStatusEnum status;

    public Reservation(User user, RestaurantTable restaurantTable, Instant bookedDate, ReservationStatusEnum status) {
        this.user = user;
        this.restaurantTable = restaurantTable;
        this.bookedDate = bookedDate;
        this.status = status;
    }
}
