package com.nhan.restaurant.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDateTime orderTime;
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
