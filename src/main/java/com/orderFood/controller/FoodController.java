package com.orderFood.controller;

import com.orderFood.entity.Food;
import com.orderFood.entity.User;
import com.orderFood.service.FoodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FoodController {
    @Autowired
    FoodService foodService;
    private static String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping("/addFood")
    public String showAddFoodForm(Model model) {
        model.addAttribute("food", new Food());
        return "food/addFood";
    }

    @GetMapping("/deleteFoodById")
    public String deleteFood(@RequestParam int id) {
        System.out.println("run at her " + " " + id);
        Food food   = foodService.getFoodById(id).orElse(null);
        System.out.println("food name" + " " + food.getName());
        if (food == null) { return "error";}
        foodService.deleteFood(id);
        return "redirect:/listFood";
    }

    @GetMapping("/editFood")
    public String showEditFoodForm(@RequestParam int id, Model model) {
        System.out.println("run at her " + " " + id);
        Food food   = foodService.getFoodById(id).orElse(null);
        if (food == null) { return "error";}
        model.addAttribute("mess","food not found");
        model.addAttribute("food", food);
        return "food/editFood";
    }

    @PostMapping("/editFood")
    public String updateFood(@ModelAttribute("food") Food food) {
        System.out.println("run at her " + " " + food.getName());
        foodService.saveFood(food);
        return "redirect:/listFood";
    }

    @GetMapping("/listFood")
    public String getAllFood(HttpSession session, Model model) {
      Iterable<Food> foods =   foodService.getAllFoods();
      User user = (User) session.getAttribute("userInfo");
      model.addAttribute("userInfo", user);
        model.addAttribute("foods", foods);
        model.addAttribute("active",1);
        return "food/listFood";
    }
    @GetMapping("/detailFood")
    public String getDetailFood(Model model, @RequestParam int id) {
        Food food = foodService.getFoodById(id).orElse(null);
        if (food == null) {
            model.addAttribute("mess","food not found");
            return "error";
        }else {
            model.addAttribute("food", food);
            return "food/detailFood";
        }
    }
    @PostMapping("/addFood")
    public String addFood(@ModelAttribute Food food ) {
        foodService.saveFood(food);
            return "redirect:/listFood";
        }
    }

