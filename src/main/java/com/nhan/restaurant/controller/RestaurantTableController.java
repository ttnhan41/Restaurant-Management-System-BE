package com.nhan.restaurant.controller;

import com.nhan.restaurant.dto.RestaurantTableDTO;
import com.nhan.restaurant.service.RestaurantTableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    public RestaurantTableController(RestaurantTableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableDTO>> getAllRestaurantTables() {
        return ResponseEntity.ok(tableService.getAllRestaurantTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableDTO> getRestaurantTable(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getRestaurantTable(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantTableDTO> createRestaurantTable(@RequestBody RestaurantTableDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.createRestaurantTable(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableDTO> updateRestaurantTable(@PathVariable Long id, @RequestBody RestaurantTableDTO dto) {
        return ResponseEntity.ok(tableService.updateRestaurantTable(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurantTable(@PathVariable Long id) {
        tableService.deleteRestaurantTable(id);
        return ResponseEntity.ok("Table deleted successfully");
    }
}
