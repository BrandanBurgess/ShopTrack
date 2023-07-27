package com.example.shoptrack.data;

public class User {
    private String email;
    private String role;

    //add an id field here.

    public User() {
    }
    public User(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}