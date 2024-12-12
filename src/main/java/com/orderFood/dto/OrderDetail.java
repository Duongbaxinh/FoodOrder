package com.orderFood.dto;

import com.orderFood.entity.Food;
import com.orderFood.entity.Orders;
import com.orderFood.entity.TableO;
import com.orderFood.entity.User;

import java.util.ArrayList;

public class OrderDetail {
    public TableO tableO;
    public ArrayList<Food> foods;
    public ArrayList<FoodOrder> foodOrders;
    public double totalPrice;
    public OrderDetail( ArrayList<Food> foods, ArrayList<FoodOrder> foodOrders,TableO tableO) {
    this.foods = foods;
    this.tableO = tableO;
    this.foodOrders = foodOrders;
    }

    public OrderDetail() {

    }

    public TableO getTableO() {
        return tableO;
    }

    public void setTableO(TableO tableO) {
        this.tableO = tableO;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(ArrayList<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }
}
