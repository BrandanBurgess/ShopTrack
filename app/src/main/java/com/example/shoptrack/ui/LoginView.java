package com.example.shoptrack.ui;

public interface LoginView {
    void navigateToHome();
    void navigateToRegister();
    void showProgressBar();
    void hideProgressBar();
    void showErrorMessage(String message);
    void showSuccessMessage(String message);
    String getEmail();
    String getPassword();
}