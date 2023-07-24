package com.example.shoptrack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
public class StoreFragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private Button btnCreateStore;
    private Button btnAddProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mystore, container, false);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btnCreateStore = view.findViewById(R.id.btn_create_store);
        btnAddProduct = view.findViewById(R.id.btn_add_product);

        getUserRole();

        return view;
    }

    private void getUserRole() {
        db.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String role = document.getString("role");
                                updateUI(role);
                            } else {
                                Toast.makeText(getContext(), "No such document", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Get failed with " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(String role) {
        if ("storeOwner".equals(role)) {
            btnCreateStore.setVisibility(View.GONE);
            btnAddProduct.setVisibility(View.VISIBLE);
        } else {
            btnCreateStore.setVisibility(View.VISIBLE);
            btnAddProduct.setVisibility(View.GONE);
        }
    }
}