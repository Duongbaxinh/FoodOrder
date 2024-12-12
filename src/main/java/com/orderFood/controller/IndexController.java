package com.orderFood.controller;

import com.orderFood.entity.User;
import com.orderFood.service.TableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final TableService tableService;

    public IndexController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/dashBoard")
    public String showDashBoard(Model model,HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        System.out.println(user.getRole());
        if(user == null) {return "redirect:/";}
//        if(user.getRole().toUpperCase() != "ADMIN"){
//            return "error";
//        }

        return "redirect:/listUser";
    }

    @GetMapping("/")
    public String showLoginForm(Model model) {
        return "login.html";
    }
    @GetMapping("/home")
    public String showTables(HttpSession session, Model model) {
        model.addAttribute("tables", tableService.getAllTableOs());
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("userInfo", user);
        return  "home.html";
    }
}
