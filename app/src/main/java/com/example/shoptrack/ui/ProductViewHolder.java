package com.example.shoptrack.ui;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.shoptrack.R;


import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView productNameTextView;
    TextView productPriceTextView;
    TextView productDescriptionTextView;
    ImageView productImageView;

    ProductViewHolder(View itemView) {
        super(itemView);
        productNameTextView = itemView.findViewById(R.id.productNameTextView); // Changed to product_name
        productPriceTextView = itemView.findViewById(R.id.productPriceTextView); // Changed to product_price
        productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView); // Changed to product_description
        productImageView = itemView.findViewById(R.id.productImageView); // make sure this line exists
    }
}