package com.example.shoptrack.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserReference {

    private static UserReference instance1 = null;
    public String userID;


    private UserReference() {
        getFireUser();
    }

    public void getFireUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String our_email;
        userID = "";

        if (currentUser != null){
            our_email = currentUser.getEmail();
            Log.d("The current user email is: ", our_email);
        }
        else {
            throw new IllegalArgumentException("No email 1");
        }

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        Query emailQuery = usersRef.orderByChild("email").equalTo(our_email);



        emailQuery.getRef(); // Get the reference to the query



        Log.d("QUERY IS" , emailQuery.getRef().toString());

        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User with the specified email exists
                    String ID;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // The snapshot will contain the user node that matches the email
                        ID = snapshot.getKey(); // Get the user ID
                        userID = ID;
                        Log.d("id is: ", userID);
                    }
                } else {
                    Log.d("id is: ", userID);
                    throw new IllegalArgumentException("No match email 2");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error: ", "Error in getting user ID");
            }


        });
    }

    public static UserReference getInstance() {
        if (instance1 == null) {
            instance1 = new UserReference();
        }
        return instance1;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}
