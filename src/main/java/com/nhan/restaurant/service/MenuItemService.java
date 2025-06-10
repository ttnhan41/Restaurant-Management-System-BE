package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.MenuItemDTO;
import com.nhan.restaurant.entity.MenuItem;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.mapper.MenuItemMapper;
import com.nhan.restaurant.repository.MenuItemRepository;
import com.nhan.restaurant.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final ReviewRepository reviewRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public MenuItemDTO createMenuItem(MenuItemDTO dto) {
        MenuItem menuItem = MenuItemMapper.toEntity(dto);
        MenuItem saved = menuItemRepository.save(menuItem);
        return MenuItemMapper.toDTO(saved, null);
    }

    public MenuItemDTO getMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu item not found with id: " + id));

        Double avgRating = reviewRepository.findAverageRatingByMenuItemId(id);

        return MenuItemMapper.toDTO(menuItem, avgRating);
    }

    public List<MenuItemDTO> getAllMenuItems() {
        List<MenuItem> items = menuItemRepository.findAll();
        List<MenuItemDTO> dtos = new ArrayList<>();

        for (MenuItem item : items) {
            Double avgRating = reviewRepository.findAverageRatingByMenuItemId(item.getId());
            dtos.add(MenuItemMapper.toDTO(item, avgRating));
        }

        return dtos;
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu item not found with id: " + id));

        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setType(dto.getType());
        menuItem.setPrice(dto.getPrice());
        menuItem.setImageUrl(dto.getImageUrl());
        menuItem.setAvailable(dto.getAvailable());

        MenuItem saved = menuItemRepository.save(menuItem);
        Double avgRating = reviewRepository.findAverageRatingByMenuItemId(saved.getId());

        return MenuItemMapper.toDTO(saved, avgRating);
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new NotFoundException("Menu item not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
    }
}
