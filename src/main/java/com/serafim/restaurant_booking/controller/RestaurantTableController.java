package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.Table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableRequestDTO;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableResponseDTO;
import com.serafim.restaurant_booking.model.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/restaurant-tables")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @PostMapping
    public ResponseEntity<RestaurantTableResponseDTO> create(@RequestBody RestaurantTableRequestDTO body) {
        RestaurantTableResponseDTO restaurantTable = restaurantTableService.create(body);
        return ResponseEntity.ok(restaurantTable);
    }
}
