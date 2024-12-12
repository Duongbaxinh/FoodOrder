package com.orderFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {
    private static String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping("/uploadImage")
    public String showAddFoodForm(Model model) {
        model.addAttribute("food", "");
        return "upload";
    }

    // Xử lý thêm món ăn mới (bao gồm ảnh)
    @PostMapping("/uploadImage")
    public String addFood(@ModelAttribute String food , @RequestParam(name = "image") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return "upload";
    }
}
