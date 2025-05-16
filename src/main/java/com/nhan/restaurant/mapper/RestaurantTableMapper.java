package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.RestaurantTableDTO;
import com.nhan.restaurant.entity.RestaurantTable;

public class RestaurantTableMapper {
    public static RestaurantTableDTO toDTO(RestaurantTable table) {
        RestaurantTableDTO dto = new RestaurantTableDTO();
        dto.setId(table.getId());
        dto.setTableNumber(table.getTableNumber());
        dto.setSeats(table.getSeats());
        dto.setAvailable(table.isAvailable());
        return dto;
    }

    public static RestaurantTable toEntity(RestaurantTableDTO dto) {
        RestaurantTable entity = new RestaurantTable();
        entity.setId(dto.getId());
        entity.setTableNumber(dto.getTableNumber());
        entity.setSeats(dto.getSeats());
        entity.setAvailable(dto.isAvailable());
        return entity;
    }
}
