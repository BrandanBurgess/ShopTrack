package com.example.shoptrack.ui;

import android.text.TextUtils;

import com.example.shoptrack.data.Auth;

public class LoginPresenter {

    private LoginView view;
    private Auth model;

    public LoginPresenter(LoginView view, Auth model) {
        this.view = view;
        this.model = model;
    }

    public void onLoginButtonClicked() {
        String email = view.getEmail();
        String password = view.getPassword();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            view.showErrorMessage("Enter email and password");
            return;
        }

        view.showProgressBar();
        model.login(email, password, new Auth.OnLoginCompleteListener() {
            @Override
            public void onLoginSuccess() {
                view.hideProgressBar();
                view.showSuccessMessage("Login Successful");
                view.navigateToHome();
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                view.hideProgressBar();
                view.showErrorMessage(errorMessage);
            }
        });
    }

    public void onRegisterNowClicked() {
        view.navigateToRegister();
    }
}