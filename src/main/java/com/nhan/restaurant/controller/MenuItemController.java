package com.nhan.restaurant.controller;

import com.nhan.restaurant.dto.MenuItemDTO;
import com.nhan.restaurant.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem(@RequestBody MenuItemDTO dto) {
        MenuItemDTO created = menuItemService.createMenuItem(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItem(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getAllAvailableMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllAvailableMenuItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO dto) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
}
