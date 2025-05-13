package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.MenuItemDTO;
import com.nhan.restaurant.entity.MenuItem;

public class MenuItemMapper {

    public static MenuItemDTO toDTO(MenuItem entity) {
        if (entity == null) return null;

        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setAvailable(entity.getAvailable());
        return dto;
    }

    public static MenuItem toEntity(MenuItemDTO dto) {
        if (dto == null) return null;

        MenuItem entity = new MenuItem();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAvailable(dto.getAvailable());
        return entity;
    }
}
