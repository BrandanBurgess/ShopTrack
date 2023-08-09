package com.example.shoptrack.ui;

import com.example.shoptrack.firebase.FirebaseUserManager;
import com.example.shoptrack.utils.DBConnection;

import java.util.concurrent.CompletableFuture;

public class LoginModel {
    FirebaseUserManager userManager;

    public LoginModel() {
        userManager = DBConnection.getInstance().getUserManager();
    }

    public CompletableFuture<Boolean> login(String email, String password) {
        CompletableFuture<Boolean> loginFuture = new CompletableFuture<>();

        userManager.logIn(email, password, result -> {
            result.onSuccess(firebaseUser -> {
                loginFuture.complete(true);
            }).onFailure(throwable -> {
                loginFuture.complete(false);
            });
        });

        return loginFuture;
    }
}
