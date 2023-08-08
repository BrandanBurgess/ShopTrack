package com.example.shoptrack.ui;

import com.example.shoptrack.firebase.FirebaseUserManager;
import com.example.shoptrack.utils.DBConnection;

public class LoginModel {
    FirebaseUserManager userManager;

    public LoginModel() {
        userManager = DBConnection.getInstance().getUserManager();
    }

    public void login(LoginPresenter presenter, String email, String password) {
        userManager.logIn(email, password, result -> {
            presenter.showSpinner(false);
            result.onSuccess(firebaseUser -> {
                presenter.sendNotifyMessage("Login Successful");
                presenter.navigateToHome();
            }).onFailure(throwable -> presenter.sendNotifyMessage("Authentication failed."));
        });
    }
}
