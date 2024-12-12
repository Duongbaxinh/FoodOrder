package com.orderFood.dto;

import com.orderFood.entity.Food;
import com.orderFood.entity.Orders;
import com.orderFood.entity.User;

import java.util.ArrayList;

public class OrderTable {
    public Orders order;
    public ArrayList<Food> foods;
    public User user;
    public OrderTable(Orders order, ArrayList<Food> foods, User user) {
        this.order = order;
        this.foods = foods;
        this.user = user;
    }

    public OrderTable() {

    }
}
