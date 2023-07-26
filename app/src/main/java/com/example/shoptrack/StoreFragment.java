package com.example.shoptrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView userRoleText;
    private Button createStoreButton, addProductButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mystore, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        userRoleText = view.findViewById(R.id.user_role_text);
        createStoreButton = view.findViewById(R.id.btn_create_store);
        addProductButton = view.findViewById(R.id.btn_add_product);

        // Initially hide the buttons
        createStoreButton.setVisibility(View.GONE);
        addProductButton.setVisibility(View.GONE);

        createStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateStoreActivity.class);
                startActivity(intent);
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to add product
            }
        });

        fetchUserRole();

        return view;
    }

    private void fetchUserRole() {
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            if (user != null) {
                                String role = user.getRole();
                                if (role != null) {
                                    if ("Store Owner".equals(role)) {
                                        userRoleText.setText("Yes, you are a store owner.");
                                        createStoreButton.setVisibility(View.VISIBLE);
                                        addProductButton.setVisibility(View.VISIBLE);
                                    } else {
                                        userRoleText.setText("You are a shopper. You need to register as a store owner to create a store.");
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
