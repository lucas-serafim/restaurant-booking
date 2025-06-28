package com.serafim.restaurant_booking.model.service;

import com.serafim.restaurant_booking.model.domain.Table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableRequestDTO;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableResponseDTO;
import com.serafim.restaurant_booking.model.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantTableService {

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    public RestaurantTableResponseDTO create(RestaurantTableRequestDTO dto) {
        RestaurantTable restaurantTable = new RestaurantTable(
                dto.name(),
                dto.capacity(),
                dto.status()
        );

        restaurantTableRepository.save(restaurantTable);

        return new RestaurantTableResponseDTO(
                restaurantTable.getId(),
                restaurantTable.getName(),
                restaurantTable.getCapacity(),
                restaurantTable.getStatus()
        );
    }

    public List<RestaurantTableResponseDTO> findAll() {
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();

        return restaurantTables.stream().map(current -> new RestaurantTableResponseDTO(
                current.getId(),
                current.getName(),
                current.getCapacity(),
                current.getStatus()
        )).toList();
    }

    public void delete(UUID restaurantTableId) {
        restaurantTableRepository.deleteById(restaurantTableId);
    }
}
