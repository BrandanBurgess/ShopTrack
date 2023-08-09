package com.example.shoptrack.ui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Store;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class StoreAdapter extends FirebaseRecyclerAdapter <Store, StoreAdapter.storeViewholder>{

    private FragmentManager fragmentManager;

    public StoreAdapter(@NonNull FirebaseRecyclerOptions<Store> options, FragmentManager fragmentManager) {
        super(options);
        this.fragmentManager = fragmentManager;
    }

    @Override

    protected void onBindViewHolder(@NonNull storeViewholder holder, int position, @NonNull Store model){
        holder.store_name.setText(model.title);
        holder.store_description.setText(model.description);
        Picasso.get().load(model.imageUrl).into(holder.store_image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String store_id = getRef(position).getKey();
                Fragment fragment = StoreView.newInstance(store_id);
                replaceFragment(fragment);
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @NonNull
    @Override
    public storeViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store, parent, false);

        return new StoreAdapter.storeViewholder(view);
    }

    class storeViewholder extends RecyclerView.ViewHolder{
        TextView store_name, store_description;
        ImageView store_image;
        public storeViewholder(@NonNull View itemView){
            super(itemView);
            store_name = itemView.findViewById(R.id.shopper_order_store);
            store_description = itemView.findViewById(R.id.store_description);
            store_image = itemView.findViewById(R.id.store_image);

        }
    }


}
