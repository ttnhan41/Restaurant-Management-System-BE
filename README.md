# 🍽️ Restaurant Management System – Backend

## 🧾 Introduction

This is the backend service for a restaurant management system built using Spring Boot. It allows restaurant guests to place orders, make reservations, and write reviews, while managers can manage tables, menus, and order statuses. The system uses JWT authentication and role-based access control to distinguish between users and managers.

---

## 💻 Tech Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)&nbsp;
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)&nbsp;
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)&nbsp;
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)&nbsp;

---

## 🚀 Features

### 🔐 Authentication
- User registration and login using JWT
- Default role: `GUEST`
- `MANAGER` role is manually assignable via database

### 👥 User Roles
- **Guest**: Can browse menu, place orders, make reservations, leave reviews
- **Manager**: Can manage menu items, view all orders, update order statuses, manage tables

### 🍽️ Orders
- Place an order with multiple menu items
- View own order history
- Manager can view all orders, update order status (PENDING, IN_PROGRESS, SERVED, CANCELLED)

### 📅 Reservations
- Guests can make table reservations
- Manager can update reservation status (PENDING, CONFIRMED, CANCELLED)
- System checks for existing reservations

### 📋 Menu & Tables
- Manager can create, update, and delete menu items and restaurant tables

### 📝 Reviews
- Guests can write reviews for the menu items

---

## 📚 API List

### Authentication
- `POST /auth/register` – Register as a new guest
- `POST /auth/login` – Login and receive JWT token

### Orders
- `POST /orders` – Place an order
- `GET /orders` – Get current user's orders
- `GET /orders/all` – (Manager) View all orders
- `PUT /orders/updateStatus` – (Manager) Update order status

### Reservations
- `POST /reservations` – Create reservation
- `DELETE /reservations/{id}` – Cancel reservation
- `GET /reservations` – Get current user's reservations
- `GET /reservations/all` – (Manager) View all reservations
- `PUT /reservations/updateStatus` – (Manager) Update reservation status

### Menu Items
- `GET /menu-items` – View all menu items
- `GET /menu-items/{id}` – View a menu item
- `POST /menu-items` – (Manager) Create menu item
- `PUT /menu-items/{id}` – (Manager) Update menu item
- `DELETE /menu-items/{id}` – (Manager) Delete menu item

### Restaurant Tables
- `GET /tables` – View all tables
- `GET /tables/{id}` – View a table
- `POST /tables` – (Manager) Add table
- `PUT /tables/{id}` – (Manager) Update table
- `DELETE /tables/{id}` – (Manager) Delete table

### Reviews
- `POST /reviews` – Add review
- `PUT /reviews/{id}` – Update review
- `DELETE /reviews/{id}` – Delete review
- `GET /reviews/menu-items/{id}` – View all reviews of a menu item
- `GET /reviews/all` – (Manager) View all reviews

### Users
- `GET /users/all` – (Manager) View all users
- `GET /users/{id}` – (Manager) View a user
- `GET /users/current-user` – View current user
- `PUT /users/update` – Update current user

---

## ⚙️ Installation Guide

### 1. Clone the repository

```
git clone https://github.com/ttnhan41/Restaurant-Management-System-BE.git
cd Restaurant-Management-System-BE
```

### 2. Configure MySQL database
Update `src/main/resources/application.properties`

### 3. Install Maven dependencies in `pom.xml`

### 4. Run the application
Server should start on: `http://localhost:8080`
