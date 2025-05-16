package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.RestaurantTableDTO;
import com.nhan.restaurant.entity.RestaurantTable;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.repository.RestaurantTableRepository;
import com.nhan.restaurant.mapper.RestaurantTableMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;

    public RestaurantTableService(RestaurantTableRepository repository) {
        this.tableRepository = repository;
    }

    public List<RestaurantTableDTO> getAllRestaurantTables() {
        return tableRepository.findAll().stream()
                .map(RestaurantTableMapper::toDTO).toList();
    }

    public RestaurantTableDTO getRestaurantTable(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Table not found with id: " + id));
        return RestaurantTableMapper.toDTO(table);
    }

    public RestaurantTableDTO createRestaurantTable(RestaurantTableDTO dto) {
        RestaurantTable table = RestaurantTableMapper.toEntity(dto);
        return RestaurantTableMapper.toDTO(tableRepository.save(table));
    }

    public RestaurantTableDTO updateRestaurantTable(Long id, RestaurantTableDTO dto) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Table not found with id: " + id));

        table.setTableNumber(dto.getTableNumber());
        table.setSeats(dto.getSeats());
        table.setAvailable(dto.isAvailable());

        return RestaurantTableMapper.toDTO(tableRepository.save(table));
    }

    public void deleteRestaurantTable(Long id) {
        if (!tableRepository.existsById(id)) {
            throw new NotFoundException("Table not found");
        }
        tableRepository.deleteById(id);
    }
}
