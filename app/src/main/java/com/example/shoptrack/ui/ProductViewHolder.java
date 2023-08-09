/*
package com.example.shoptrack.ui;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;



import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView productNameTextView;
    TextView productPriceTextView;
    TextView productDescriptionTextView;
    ImageView productImageView;
    ImageView editButton;
    ImageView deleteButton;
    ProductActionListener mListener;
    List<Product> productList;

    ProductViewHolder(View itemView, List<Product> productList, ProductActionListener listener) {
        super(itemView);
        this.productList = productList;
        this.mListener = listener;
        productNameTextView = itemView.findViewById(R.id.productNameTextView); // Changed to product_name
        productPriceTextView = itemView.findViewById(R.id.productPriceTextView); // Changed to product_price
        productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView); // Changed to product_description
        productImageView = itemView.findViewById(R.id.productImageView); // make sure this line exists
        editButton = itemView.findViewById(R.id.editButton);
        deleteButton = itemView.findViewById(R.id.deleteButton);

        // Set the click listener for the edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onEditProduct(productList.get(position));
                }
            }
        });

        // Set the click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && mListener != null) {
                    mListener.onDeleteProduct(productList.get(position));
                }
            }
        });
    }
}*/
