package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.ReviewDTO;
import com.nhan.restaurant.entity.MenuItem;
import com.nhan.restaurant.entity.Review;
import com.nhan.restaurant.entity.User;
import com.nhan.restaurant.exception.UnauthorizedException;
import com.nhan.restaurant.mapper.ReviewMapper;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.repository.MenuItemRepository;
import com.nhan.restaurant.repository.ReviewRepository;
import com.nhan.restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    public ReviewService(ReviewRepository reviewRepo, UserRepository userRepo, MenuItemRepository menuItemRepo) {
        this.reviewRepository = reviewRepo;
        this.userRepository = userRepo;
        this.menuItemRepository = menuItemRepo;
    }

    public ReviewDTO createReview(Long userId, ReviewDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
                .orElseThrow(() -> new NotFoundException("Menu item not found"));

        Review review = new Review();
        review.setUser(user);
        review.setMenuItem(menuItem);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return ReviewMapper.toDTO(reviewRepository.save(review));
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(ReviewMapper::toDTO).toList();
    }

    public List<ReviewDTO> getReviewsByMenuItem(Long menuItemId) {
        menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new NotFoundException("Menu item not found"));

        return reviewRepository.findByMenuItemId(menuItemId)
                .stream().map(ReviewMapper::toDTO).toList();
    }

    public ReviewDTO updateReview(Long reviewId, Long userId, ReviewDTO dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only update your own review");
        }

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return ReviewMapper.toDTO(reviewRepository.save(review));
    }

    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only delete your own review");
        }

        reviewRepository.deleteById(reviewId);
    }
}
