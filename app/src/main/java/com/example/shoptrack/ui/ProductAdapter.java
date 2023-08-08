package com.example.shoptrack.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentProduct = productList.get(position);
        holder.productNameTextView.setText(currentProduct.getName());
        holder.productPriceTextView.setText(String.valueOf(currentProduct.getPrice()));
        holder.productDescriptionTextView.setText(currentProduct.getDescription());

        // Debugging check 1: is the ImageView null?
        if (holder.productImageView == null) {
            Log.d("ProductAdapter", "Product image view is null");
        }

        // Debugging check 2: is the product or its image URL null?
        if (currentProduct == null) {
            Log.d("ProductAdapter", "Product is null");
        } else if (currentProduct.getImageUrl() == null) {
            Log.d("ProductAdapter", "Product image URL is null");
        } else {
            // Load the image with Glide
            Glide.with(holder.productImageView.getContext())
                    .load(currentProduct.getImageUrl())
                    .placeholder(R.drawable.logo_no_wm)
                    .into(holder.productImageView);
        }

    }




    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productPriceTextView;
        TextView productDescriptionTextView;
        ImageView productImageView;

        ProductViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
        }
    }
}
