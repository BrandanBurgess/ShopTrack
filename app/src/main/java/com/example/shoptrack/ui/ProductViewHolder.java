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

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        productNameTextView = itemView.findViewById(R.id.productNameTextView);
        productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
        productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
        productImageView = itemView.findViewById(R.id.productImageView); // make sure this line exists
    }
}