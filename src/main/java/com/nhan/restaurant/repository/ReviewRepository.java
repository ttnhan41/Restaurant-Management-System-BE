package com.nhan.restaurant.repository;

import com.nhan.restaurant.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMenuItemId(Long menuItemId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.menuItem.id = :menuItemId")
    Double findAverageRatingByMenuItemId(@Param("menuItemId") Long menuItemId);
}
