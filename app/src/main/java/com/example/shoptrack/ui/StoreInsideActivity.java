package com.example.shoptrack.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoptrack.R;

public class StoreInsideActivity extends AppCompatActivity {

    private Button createProductButton, editProductButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_inside);

        createProductButton = findViewById(R.id.btn_create_product);
        editProductButton = findViewById(R.id.btn_edit_product);

        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the CreateProductActivity
                Intent intent = new Intent(StoreInsideActivity.this, CreateProductActivity.class);
                startActivity(intent);
            }
        });

        editProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EditProductActivity
                Intent intent = new Intent(StoreInsideActivity.this, EditProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
