package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.MenuItemDTO;
import com.nhan.restaurant.entity.MenuItem;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.mapper.MenuItemMapper;
import com.nhan.restaurant.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItemDTO createMenuItem(MenuItemDTO dto) {
        MenuItem menuItem = MenuItemMapper.toEntity(dto);
        MenuItem saved = menuItemRepository.save(menuItem);
        return MenuItemMapper.toDTO(saved);
    }

    public MenuItemDTO getMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu item not found with id: " + id));
        return MenuItemMapper.toDTO(menuItem);
    }

    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(MenuItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu item not found with id: " + id));

        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        menuItem.setImageUrl(dto.getImageUrl());
        menuItem.setAvailable(dto.getAvailable());

        return MenuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new NotFoundException("Menu item not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
    }
}
