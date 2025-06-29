package com.serafim.restaurant_booking.model.service;

import com.serafim.restaurant_booking.model.domain.Table.RestaurantTable;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableRequestDTO;
import com.serafim.restaurant_booking.model.domain.Table.RestaurantTableResponseDTO;
import com.serafim.restaurant_booking.model.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        return this.toResponseDTO(restaurantTable);
    }

    public List<RestaurantTableResponseDTO> findAll() {
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();

        return restaurantTables.stream().map(this::toResponseDTO).toList();
    }

    public RestaurantTableResponseDTO update(UUID restaurantTableId, RestaurantTableRequestDTO dto) {
        RestaurantTable restaurantTable = this.findById(restaurantTableId);

        restaurantTable.setName(dto.name() != null ? dto.name() : restaurantTable.getName());
        restaurantTable.setCapacity(dto.capacity() != null ? dto.capacity() : restaurantTable.getCapacity());
        restaurantTable.setStatus(dto.status() != null ? dto.status() : restaurantTable.getStatus());

        restaurantTableRepository.save(restaurantTable);

        return this.toResponseDTO(restaurantTable);
    }

    public void delete(UUID restaurantTableId) {
        restaurantTableRepository.deleteById(restaurantTableId);
    }

    private RestaurantTable findById(UUID restaurantTableId) {
        return restaurantTableRepository.findById(restaurantTableId).get();
    }

    private RestaurantTableResponseDTO toResponseDTO(RestaurantTable restaurantTable) {
        return new RestaurantTableResponseDTO(
                restaurantTable.getId(),
                restaurantTable.getName(),
                restaurantTable.getCapacity(),
                restaurantTable.getStatus()
        );
    }
}
