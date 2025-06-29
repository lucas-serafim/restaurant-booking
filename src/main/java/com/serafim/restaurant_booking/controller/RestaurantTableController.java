package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.Response.ResponseMessage;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableRequestDTO;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableResponseDTO;
import com.serafim.restaurant_booking.model.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/restaurant-tables")
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

    @PutMapping("/{restaurantTableId}")
    public ResponseEntity<RestaurantTableResponseDTO> update(
            @PathVariable UUID restaurantTableId,
            @RequestBody RestaurantTableRequestDTO body
    ) {
        RestaurantTableResponseDTO restaurantTable = restaurantTableService.update(restaurantTableId, body);
        return ResponseEntity.ok(restaurantTable);
    }

    @DeleteMapping("/{restaurantTableId}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable UUID restaurantTableId) {
        restaurantTableService.delete(restaurantTableId);
        return ResponseEntity.ok(new ResponseMessage("Table deleted successfully."));
    }
}
