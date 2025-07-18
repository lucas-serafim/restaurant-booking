package com.serafim.restaurant_booking.model.domain.table;

import com.serafim.restaurant_booking.model.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.serafim.restaurant_booking.model.enums.TableStatusEnum;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Integer capacity;
    private TableStatusEnum status;

    @OneToMany(mappedBy = "restaurantTable", cascade = CascadeType.ALL)
    private List<Reservation> reservation = new ArrayList<>();

    public RestaurantTable(String name, Integer capacity, TableStatusEnum status) {
        this.name = name;
        this.capacity = capacity;
        this.status = status;
    }
}
