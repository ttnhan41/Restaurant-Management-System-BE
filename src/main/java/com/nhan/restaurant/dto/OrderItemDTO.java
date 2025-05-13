package com.nhan.restaurant.dto;

public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long menuItemId;
    private int quantity;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
