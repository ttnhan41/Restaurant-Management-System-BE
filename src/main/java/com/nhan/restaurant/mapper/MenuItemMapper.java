package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.MenuItemDTO;
import com.nhan.restaurant.entity.MenuItem;

public class MenuItemMapper {

    public static MenuItemDTO toDTO(MenuItem entity, Double avgRating) {
        if (entity == null) return null;

        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType());
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setAvailable(entity.getAvailable());
        dto.setAverageRating(avgRating);
        return dto;
    }

    public static MenuItem toEntity(MenuItemDTO dto) {
        if (dto == null) return null;

        MenuItem entity = new MenuItem();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
        entity.setAvailable(dto.getAvailable());
        return entity;
    }
}
