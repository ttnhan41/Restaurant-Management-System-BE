package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.OrderDTO;
import com.nhan.restaurant.dto.OrderItemDTO;
import com.nhan.restaurant.entity.MenuItem;
import com.nhan.restaurant.entity.Order;
import com.nhan.restaurant.entity.OrderItem;
import com.nhan.restaurant.entity.User;
import com.nhan.restaurant.mapper.OrderMapper;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.repository.MenuItemRepository;
import com.nhan.restaurant.repository.OrderItemRepository;
import com.nhan.restaurant.repository.OrderRepository;
import com.nhan.restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo,
                        OrderItemRepository orderItemRepo, MenuItemRepository menuItemRepo) {
        this.orderRepository = orderRepo;
        this.userRepository = userRepo;
        this.orderItemRepository = orderItemRepo;
        this.menuItemRepository = menuItemRepo;
    }

    public OrderDTO placeOrder(Long userId, OrderDTO requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);
        order = orderRepository.save(order);

        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDTO itemDto : requestDto.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemDto.getMenuItemId())
                    .orElseThrow(() -> new NotFoundException("Menu item not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setMenuItem(menuItem);
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(menuItem.getPrice());
            items.add(orderItemRepository.save(item));
        }

        order.setItems(items);
        return OrderMapper.toDTO(order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO updateStatus(Long orderId, Order.Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.setStatus(status);
        return OrderMapper.toDTO(orderRepository.save(order));
    }
}
