package com.example.shoptrack.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserReference {

    public static UserReference instance1 = null;
    public String userID;
    public String our_email;


    private UserReference() {
        getFireUser();
    }

    public void getFireUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();




        if (currentUser != null){
            our_email = currentUser.getEmail();
            Log.d("The current user email is: ", our_email);
        }
        else {
            throw new IllegalArgumentException("No email 1");
        }

        DatabaseReference mbase = FirebaseDatabase.getInstance().getReference("users");
        String key = mbase.getKey().toString();
        Log.d("The key is: ", key);

        mbase.orderByChild("email").equalTo(our_email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id;
                Log.d("The snapshot is: ", snapshot.toString());
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    id = childSnapshot.getKey();
                    sendKey(id);
                    Log.d("The user id is: ", userID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    public void sendKey(String key){
        userID = key;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.our_email = email;
    }

    public String getOur_email() {
        return our_email;
    }


}
