package com.example.shoptrack.managers;

import com.google.firebase.auth.FirebaseUser;

import java.util.function.Consumer;

import io.vavr.control.Try;

public interface UserManager {
    void signUp(String email, String password, String role, Consumer<Try<FirebaseUser>> callback);
    void logIn(String email, String password, Consumer<Try<FirebaseUser>> callback);
}