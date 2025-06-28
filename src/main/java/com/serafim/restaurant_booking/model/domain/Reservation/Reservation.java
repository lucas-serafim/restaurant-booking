package com.serafim.restaurant_booking.model.domain.Reservation;

import com.serafim.restaurant_booking.model.domain.Table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.User.User;
import com.serafim.restaurant_booking.model.enums.ReservationStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
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

    @OneToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable restaurantTable;

    @Column(name = "booked_date")
    private Instant bookedDate;

    private ReservationStatusEnum status;
}
