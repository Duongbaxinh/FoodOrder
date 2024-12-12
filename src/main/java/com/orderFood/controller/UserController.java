package com.orderFood.controller;

import com.orderFood.dto.AuthRequest;
import com.orderFood.entity.User;
import com.orderFood.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Locale;

@Controller
@EnableWebMvc
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String showCreateUserForm(Model model,HttpSession session) {
        User user1 = (User) session.getAttribute("userInfo");
        if(user1 == null) {return "redirect:/";}
        else if (!user1.getRole().toUpperCase(Locale.ENGLISH).equals("ADMIN")){
            model.addAttribute("mess","unauthorized");
            return "error.html";
        }
        model.addAttribute("user", new User());
        return "register.html";
    }

    // Xử lý lưu user mới
    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {

        User userR = new User();
        userR.setEmail(user.getEmail());
        userR.setPassword(passwordEncoder.encode(user.getPassword()));
        userR.setFirstName(user.getFirstName());
        userR.setLastName(user.getLastName());
        userR.setRole(user.getRole());
        userService.saveUser(userR);

        return "redirect:/listUser";
    }
    @GetMapping("/listUser")
    public String showListUser(Model model) {
        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("active",2);
        return "user/listUser.html";
    }
    @GetMapping("/editUser")
    public String showEditUserForm(@RequestParam int id,Model model) {
        User userR = userService.getUserById(id);
        if(userR == null) {  model.addAttribute("mess", "user not found!"); return "error.html";}
        model.addAttribute("user", userR);
        return "user/editUser.html";
    }
    @PostMapping("/updateUser")
    public String editUser( @ModelAttribute User user, Model model) {
    System.out.println("check user " + user.getEmail() + " " + user);
        User userR = userService.getUserById(user.getId());
        user.setPassword(userR.getPassword());
        userService.saveUser(user);
        return "redirect:/listUser";
    }
    @GetMapping("/deleteUser")
    public String editUser(@RequestParam int id, Model model) {
        User userR = userService.getUserById(id);
        if(userR == null) {  model.addAttribute("mess", "user not found!"); return "error.html";}
        userService.deleteUserById(id);
        return "redirect:/listUser";
    }


    @PostMapping("/loginpage")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session,Model model) {
        System.out.println("pre" + email);
        User userExited = userService.getUserByEmail(email);
        System.out.println("check user exted " + passwordEncoder.encode(password));
        if( userExited!= null && passwordEncoder.matches(password, userExited.getPassword())) {
            session.setAttribute("userInfo", userExited);
            model.addAttribute("userInfo", userExited);
            return "redirect:/home";
        }else {
            System.out.println("check user exited");
            model.addAttribute("mess","user not found");
            return "error";
        }

    }

    // Hiển thị thông tin chi tiết của một user theo ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            model.addAttribute("mess","user not found");
            return "error";
        }
        model.addAttribute("user", user);
        return "user-detail"; // Trả về view 'user-detail.html' hoặc 'user-detail.jsp'
    }
}
