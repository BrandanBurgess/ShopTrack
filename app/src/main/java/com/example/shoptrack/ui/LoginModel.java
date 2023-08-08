package com.example.shoptrack.ui;

import com.example.shoptrack.firebase.FirebaseUserManager;
import com.example.shoptrack.utils.DBConnection;

import java.util.concurrent.atomic.AtomicBoolean;

public class LoginModel {
    FirebaseUserManager userManager;

    public LoginModel() {
        userManager = DBConnection.getInstance().getUserManager();
    }

    public AtomicBoolean login(LoginPresenter presenter, String email, String password) {
        AtomicBoolean loginSuccess = new AtomicBoolean(false);

        userManager.logIn(email, password, result -> {
            result.onSuccess(firebaseUser -> {
                loginSuccess.set(true);
            }).onFailure(throwable -> {
                loginSuccess.set(false);
            });
        });

        return loginSuccess;
    }
}
