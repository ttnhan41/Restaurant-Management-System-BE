package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.OrderDTO;
import com.nhan.restaurant.entity.Order;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setStatus(order.getStatus().name());
        return dto;
    }
}
