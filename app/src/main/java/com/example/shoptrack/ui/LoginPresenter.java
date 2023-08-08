package com.example.shoptrack.ui;

public class LoginPresenter {
    Login view;
    LoginModel model;

    public LoginPresenter(Login view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    public void login(String email, String password) {
        if (email.isEmpty()) {
            sendNotifyMessage("Enter email");
            return;
        }

        if (password.isEmpty()) {
            sendNotifyMessage("Enter password");
            return;
        }

        showSpinner(true);
        model.login(this, email, password);
    }

    public void sendNotifyMessage(String message) {
        view.notify(message);
    }

    public void showSpinner(boolean show) {
        if (show) {
            view.showProgressBar();
        } else {
            view.hideProgressBar();
        }
    }

    public void navigateToHome() {
        view.goHome();
    }
}
