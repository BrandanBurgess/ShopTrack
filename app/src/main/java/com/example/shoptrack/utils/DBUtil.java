package com.example.shoptrack.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DBUtil {
    public static DatabaseReference getUsers() {
        return DBConnection.getInstance().getDb().getReference("users");
    }
    public static DatabaseReference getStores() {
        return DBConnection.getInstance().getDb().getReference("stores");
    }
    public static DatabaseReference getProducts() {
        return DBConnection.getInstance().getDb().getReference("products");
    }

    public static DatabaseReference getCurrentUserId() {
        return getUsers().child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
