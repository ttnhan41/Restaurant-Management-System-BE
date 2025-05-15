package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.OrderItemDTO;
import com.nhan.restaurant.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDTO toDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setOrderId(item.getOrder().getId());
        dto.setMenuItemId(item.getMenuItem().getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
