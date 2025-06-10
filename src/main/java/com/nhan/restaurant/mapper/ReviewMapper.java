package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.ReviewDTO;
import com.nhan.restaurant.entity.Review;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setUserId(review.getUser().getId());
        dto.setUserName(review.getUser().getName());
        dto.setMenuItemId(review.getMenuItem().getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
