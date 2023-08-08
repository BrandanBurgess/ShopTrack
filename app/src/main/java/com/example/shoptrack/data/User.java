package com.example.shoptrack.data;

public class User {
    public String email;
    public String role;

    public User() {
    }
    public User(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}