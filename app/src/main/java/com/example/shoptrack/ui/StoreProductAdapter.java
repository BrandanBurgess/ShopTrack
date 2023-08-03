package com.example.shoptrack.ui;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;
import com.example.shoptrack.data.Store;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class StoreProductAdapter extends FirebaseRecyclerAdapter <Product, StoreProductAdapter.storeProductViewholder> {

    public StoreProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull storeProductViewholder holder, int position, @NonNull Product model){
        //fix this
        holder.shopper_store_store_name.setText(model.name);
        Picasso.get().load(model.imageUrl).into(holder.shopper_store_view_store_logo);

    }

    @NonNull
    @Override
    public storeProductViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_shoopper_store_view, parent, false);

        return new StoreProductAdapter.storeProductViewholder(view);
    }

    class storeProductViewholder extends RecyclerView.ViewHolder{
        TextView shopper_store_store_name; // change this to product name
        ImageView shopper_store_view_store_logo; // change this to product image.
        public storeProductViewholder(@NonNull View itemView){
            super(itemView);
            shopper_store_store_name = itemView.findViewById(R.id.shopper_store_store_name);
            shopper_store_view_store_logo = itemView.findViewById(R.id.shopper_store_view_store_logo);

        }


    }

}
