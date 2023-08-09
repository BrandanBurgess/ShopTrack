package com.example.shoptrack.utils;

import com.example.shoptrack.firebase.FirebaseUserManager;
import com.google.firebase.database.FirebaseDatabase;

public class DBConnection {
    private static DBConnection instance = null;
    private final FirebaseDatabase db = FirebaseDatabase.getInstance("https://shoptrack-193e3-default-rtdb.firebaseio.com");

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            /*
             * why synchronized?
             * we don't want a case where we have multiple
             * getInstance calls at the start when instance is null
             * then it turns to having multiple db connections
             * as a result, we prevent race conditions in initializing the db connection
             */
            synchronized (DBConnection.class) {
                instance = new DBConnection();
            }
        }
        return instance;
    }

    public FirebaseUserManager getUserManager() {
        return new FirebaseUserManager();
    }

    public FirebaseDatabase getDb() {
        return db;
    }
}
