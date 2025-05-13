package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.ReservationDTO;
import com.nhan.restaurant.entity.Reservation;

public class ReservationMapper {
    public static ReservationDTO toDTO(Reservation res) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(res.getId());
        dto.setUserId(res.getUser().getId());
        dto.setTableId(res.getTable().getId());
        dto.setReservationTime(res.getReservationTime());
        dto.setGuestsCount(res.getGuestsCount());
        dto.setStatus(res.getStatus().name());
        return dto;
    }
}
