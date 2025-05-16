package com.nhan.restaurant.controller;

import com.nhan.restaurant.dto.ReviewDTO;
import com.nhan.restaurant.security.UserPrincipal;
import com.nhan.restaurant.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService service) {
        this.reviewService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/menu-items/{id}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByMenuItem(id));
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO dto,
                                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO dto,
                                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        return ResponseEntity.ok(reviewService.updateReview(id, userId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id,
                                               @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getId();
        reviewService.deleteReview(id, userId);
        return ResponseEntity.ok("Review deleted successfully");
    }
}

