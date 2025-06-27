package com.serafim.restaurant_booking.model.domain.Table;

import com.serafim.restaurant_booking.model.domain.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.serafim.restaurant_booking.model.enums.TableStatusEnum;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Integer capacity;
    private TableStatusEnum status;

    @OneToOne(mappedBy = "restaurantTable", cascade = CascadeType.ALL)
    private Reservation reservation;
}
