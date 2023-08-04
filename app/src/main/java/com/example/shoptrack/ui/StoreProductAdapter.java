package com.example.shoptrack.ui;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class StoreProductAdapter extends FirebaseRecyclerAdapter <Product, StoreProductAdapter.storeProductViewholder> {

    public StoreProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull storeProductViewholder holder, int position, @NonNull Product model){
        Log.d("DebugTag", "Binding data for position: " + position);

        if (holder.product_name == null || holder.product_description == null || holder.product_price == null) {
            Log.e("DebugTag", "One or more TextViews is null!");
        }
        Log.d("DebugTag", "Model Name: " + model.name);
        Log.d("DebugTag", "Model Description: " + model.description);
        Log.d("DebugTag", "Model Price: " + model.price);

        holder.product_name.setText(model.name);
        holder.product_description.setText(model.description);
        holder.product_price.setText("$" + model.price.toString());
    }

    @NonNull
    @Override
    public storeProductViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);

        return new StoreProductAdapter.storeProductViewholder(view);
    }

    class storeProductViewholder extends RecyclerView.ViewHolder{
        TextView product_description, product_name, product_price; // change this to product name
        ImageView product_image; // change this to product image.

        public storeProductViewholder(@NonNull View itemView){
            super(itemView);
            product_name = itemView.findViewById(R.id.shopper_product_name);
            product_description = itemView.findViewById(R.id.shopper_product_description);
            product_price = itemView.findViewById(R.id.shopper_product_price);
            product_image = itemView.findViewById(R.id.shopper_product_image);

        }


    }

}
