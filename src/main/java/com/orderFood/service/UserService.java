package com.orderFood.service;

import com.orderFood.entity.User;
import com.orderFood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public  User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }



}
