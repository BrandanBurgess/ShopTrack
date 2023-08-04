package com.example.shoptrack.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class StoreFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView userRoleText;
    private Button createStoreButton, addProductButton;

    private TextView storeTitleText, storeDescriptionText;
    private ImageView storeImageView;

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

        // Initialize the TextViews and ImageView
        storeTitleText = view.findViewById(R.id.store_title_text);
        storeDescriptionText = view.findViewById(R.id.store_description_text);
        storeImageView = view.findViewById(R.id.store_image_view);

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
        // Add click listener to the store image
        storeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Store Inside Activity (or whatever you named it)
                Intent intent = new Intent(getActivity(), StoreInsideActivity.class);
                startActivity(intent);
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
                                if (role != null && "Store Owner".equals(role)) {
                                    // Check if store exists
                                    mDatabase.child("stores").child(mAuth.getCurrentUser().getUid())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        // Store exists, hide create store button and role text
                                                        createStoreButton.setVisibility(View.GONE);
                                                        addProductButton.setVisibility(View.GONE);
                                                        userRoleText.setVisibility(View.GONE);
                                                    } else {
                                                        // Store does not exist, show create store button and role text
                                                        userRoleText.setText("Create your store now!");
                                                        createStoreButton.setVisibility(View.VISIBLE);
                                                        addProductButton.setVisibility(View.GONE);
                                                        userRoleText.setVisibility(View.VISIBLE);
                                                        storeTitleText.setVisibility(View.GONE);
                                                        storeDescriptionText.setVisibility(View.GONE);
                                                        storeImageView.setVisibility(View.GONE);
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    Toast.makeText(getActivity(), "Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                } else {
                                    userRoleText.setText("You are a shopper. You need to register as a store owner to create a store.");
                                    storeTitleText.setVisibility(View.GONE);
                                    storeDescriptionText.setVisibility(View.GONE);
                                    storeImageView.setVisibility(View.GONE);
                                    createStoreButton.setVisibility(View.GONE);
                                    addProductButton.setVisibility(View.GONE);
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

    private void fetchStoreDetails() {
        mDatabase.child("stores").child(mAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Store store = dataSnapshot.getValue(Store.class);
                            if (store != null) {
                                storeTitleText.setText(store.getTitle());
                                storeDescriptionText.setText(store.getDescription());

                                // Load the store image
                                Picasso.get().load(store.getImageUrl()).into(storeImageView);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Fetch store details when the fragment is resumed
        fetchStoreDetails();
    }


}
