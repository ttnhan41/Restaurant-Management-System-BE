package com.nhan.restaurant.dto;

public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long menuItemId;
    private int rating;
    private String comment;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
