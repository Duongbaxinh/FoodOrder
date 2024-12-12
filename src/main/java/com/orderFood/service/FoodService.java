package com.orderFood.service;

import com.orderFood.entity.Food;
import com.orderFood.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    // Lấy tất cả món ăn
    public Iterable<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Lấy món ăn theo ID
    public Optional<Food> getFoodById(int id) {
        return foodRepository.findById(id);
    }

    // Lưu món ăn mới hoặc cập nhật món ăn hiện tại
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    // Xóa món ăn theo ID
    public void deleteFood(int id) {
        foodRepository.deleteById(id);
    }
}
