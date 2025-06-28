package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.Response.ResponseMessage;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableRequestDTO;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableResponseDTO;
import com.serafim.restaurant_booking.model.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponseDTO>> findAll() {
        List<RestaurantTableResponseDTO> list = restaurantTableService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{restaurantTableId}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable UUID restaurantTableId) {
        restaurantTableService.delete(restaurantTableId);
        return ResponseEntity.ok(new ResponseMessage("Table deleted successfully."));
    }
}
