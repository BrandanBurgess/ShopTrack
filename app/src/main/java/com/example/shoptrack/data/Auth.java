package com.example.shoptrack.data;

import com.google.firebase.auth.FirebaseAuth;

public class Auth {

    private FirebaseAuth mAuth;

    public Auth() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password, OnLoginCompleteListener listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onLoginSuccess();
                    } else {
                        listener.onLoginFailure(task.getException().getMessage());
                    }
                });
    }

    public interface OnLoginCompleteListener {
        void onLoginSuccess();
        void onLoginFailure(String errorMessage);
    }
}