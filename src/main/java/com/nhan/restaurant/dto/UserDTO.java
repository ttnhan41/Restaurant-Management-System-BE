package com.nhan.restaurant.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String role;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
