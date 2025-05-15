package com.nhan.restaurant.controller;

import com.nhan.restaurant.dto.OrderDTO;
import com.nhan.restaurant.exception.BadRequestException;
import com.nhan.restaurant.security.UserPrincipal;
import com.nhan.restaurant.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getUserOrders(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO request,
                                               @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        if (request.getItems().isEmpty()) {
            throw new BadRequestException("Item list is empty");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(userId, request));
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<OrderDTO> updateStatus(@RequestBody OrderDTO request) {
        return ResponseEntity.ok(orderService.updateStatus(request.getId(), request.getStatus()));
    }
}
