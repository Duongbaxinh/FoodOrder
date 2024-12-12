package com.orderFood.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    public TableO tableO;

    @ManyToOne
    @JoinColumn(name = "food_id")
    public Food food;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public int quantity;
    public LocalDateTime orderTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TableO getTableO() {
        return tableO;
    }

    public void setTableO(TableO tableO) {
        this.tableO = tableO;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
