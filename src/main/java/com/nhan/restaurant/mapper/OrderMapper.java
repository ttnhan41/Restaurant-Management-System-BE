package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.OrderDTO;
import com.nhan.restaurant.dto.OrderItemDTO;
import com.nhan.restaurant.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setStatus(Order.Status.valueOf(order.getStatus().name()));

        List<OrderItemDTO> items = order.getItems().stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toList());
        dto.setItems(items);

        return dto;
    }
}
