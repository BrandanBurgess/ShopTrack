package com.example.shoptrack.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreInsideActivity extends AppCompatActivity implements ProductActionListener {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private List<Product> productList;
    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private Button createProductButton, editProductButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_inside);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        productList = new ArrayList<>();

        productRecyclerView = findViewById(R.id.product_recycler_view);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(productList, this);
        productRecyclerView.setAdapter(productAdapter);

        createProductButton = findViewById(R.id.btn_create_product);
        editProductButton = findViewById(R.id.btn_edit_product);

        // Disable the edit product button
        editProductButton.setEnabled(false);
        editProductButton.setVisibility(View.GONE);

        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the CreateProductActivity
                Intent intent = new Intent(StoreInsideActivity.this, CreateProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onEditProduct(Product product) {
        Intent intent = new Intent(this, CreateProductActivity.class);
        intent.putExtra("PRODUCT", product);  // Make sure your Product class implements Parcelable
        startActivity(intent);
    }

    @Override
    public void onDeleteProduct(Product product) {
        // Delete the product from the Firebase database
        DatabaseReference productRef = mDatabase.child("products").child(product.getProductID());
        productRef.removeValue().addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to delete product", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.child("products").orderByChild("ownerId").equalTo(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    productList.add(product);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StoreInsideActivity.this, "Failed to load products.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
