package com.serafim.restaurant_booking.model.repository;

import com.serafim.restaurant_booking.model.domain.table.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, UUID> {
}
