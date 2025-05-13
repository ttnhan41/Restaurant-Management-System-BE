package com.nhan.restaurant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // GUEST or MANAGER

    public enum Role {
        GUEST, MANAGER
    }
}
