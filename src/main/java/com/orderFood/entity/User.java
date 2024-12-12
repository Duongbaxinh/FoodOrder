package com.orderFood.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;
    @Column(unique=true)
    private String email;
    private String password;
    public String firstName;
    public String lastName;
    public String fullName;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'user'")
    public String role;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders = new ArrayList<>();


    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(int id,String username, String email, String password, String firstName,String lastName) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
