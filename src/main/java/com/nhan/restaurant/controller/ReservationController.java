package com.nhan.restaurant.controller;

import com.nhan.restaurant.dto.ReservationDTO;
import com.nhan.restaurant.security.UserPrincipal;
import com.nhan.restaurant.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService service) {
        this.reservationService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        return ResponseEntity.ok(reservationService.getReservationsByUser(userId));
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO dto,
                                                            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(userId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id,
                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        reservationService.cancelReservation(id, userId);
        return ResponseEntity.ok("Reservation cancelled successfully");
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<ReservationDTO> updateStatus(@RequestBody ReservationDTO request) {
        return ResponseEntity.ok(reservationService.updateStatus(request.getId(), request.getStatus()));
    }
}
