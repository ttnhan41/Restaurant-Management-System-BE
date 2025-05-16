package com.nhan.restaurant.repository;

import com.nhan.restaurant.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);

    @Query("SELECT r FROM Reservation r WHERE r.table.id = :tableId AND r.status <> 'CANCELLED'")
    Optional<Reservation> findActiveReservation(@Param("tableId") Long tableId);
}
