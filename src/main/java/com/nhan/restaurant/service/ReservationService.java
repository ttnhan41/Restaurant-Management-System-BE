package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.ReservationDTO;
import com.nhan.restaurant.entity.Reservation;
import com.nhan.restaurant.entity.RestaurantTable;
import com.nhan.restaurant.entity.User;
import com.nhan.restaurant.exception.AlreadyExistsException;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.exception.UnauthorizedException;
import com.nhan.restaurant.mapper.ReservationMapper;
import com.nhan.restaurant.repository.ReservationRepository;
import com.nhan.restaurant.repository.RestaurantTableRepository;
import com.nhan.restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository tableRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository repo, RestaurantTableRepository tableRepo, UserRepository userRepo) {
        this.reservationRepository = repo;
        this.tableRepository = tableRepo;
        this.userRepository = userRepo;
    }

    public ReservationDTO createReservation(Long userId, ReservationDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        RestaurantTable table = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new NotFoundException("Table not found"));

        // Check for existing reservation
        reservationRepository.findActiveReservation(dto.getTableId())
                .ifPresent(existing -> {
                    throw new AlreadyExistsException("Table is already reserved at this time.");
                });

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTable(table);
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setGuestsCount(dto.getGuestsCount());
        reservation.setStatus(Reservation.Status.PENDING);

        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }


    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::toDTO).toList();
    }

    public List<ReservationDTO> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream().map(ReservationMapper::toDTO).toList();
    }

    public void cancelReservation(Long reservationId, Long userId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        if (!reservation.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only cancel your own reservation");
        }

        reservation.setStatus(Reservation.Status.CANCELLED);
        reservationRepository.save(reservation);
    }

    public ReservationDTO updateStatus(Long reservationId, Reservation.Status status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        reservation.setStatus(status);
        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }
}
