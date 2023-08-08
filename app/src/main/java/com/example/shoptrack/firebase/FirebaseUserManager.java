package com.example.shoptrack.firebase;

import com.example.shoptrack.data.User;
import com.example.shoptrack.managers.UserManager;
import com.example.shoptrack.utils.DBUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import io.vavr.control.Try;

public class FirebaseUserManager implements UserManager {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public void signUp(@NotNull String email, @NotNull String password, @NotNull String role,  Consumer<Try<FirebaseUser>> callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
             .addOnSuccessListener(res -> {
                 User u = new User(email, role);
                 DBUtil.getCurrentUserId().setValue(u);
                 callback.accept(Try.success(mAuth.getCurrentUser()));
             })
             .addOnFailureListener(err -> {
                 callback.accept(Try.failure(err));
             });
    }

    @Override
    public void logIn(String email, String password, Consumer<Try<FirebaseUser>> callback) {
        mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(task -> {
                 if (task.isSuccessful()) {
                     callback.accept(Try.success(mAuth.getCurrentUser()));
                 } else {
                     callback.accept(Try.failure(task.getException()));
                 }
             });
    }
}
