package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;
import com.example.shoptrack.data.Store;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ShopperStoreViewFragment extends Fragment {

    public RecyclerView recyclerView;

    StoreProductAdapter adapter;
    DatabaseReference mbase;

    public String store_id;

    TextView store_name;
    ImageView store_image;




    public ShopperStoreViewFragment() {
        // Required empty public constructor
    }

    public void loadStoreDetails() {
        DatabaseReference storeRef = FirebaseDatabase.getInstance().getReference().child("stores").child(store_id);
        storeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Store store = snapshot.getValue(Store.class);
                if (store != null) {
                    store_name.setText(store.title);
                    Picasso.get().load(store.imageUrl).into(store_image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors here
            }
        });
    }


    public static ShopperStoreViewFragment newInstance(String param1) {
        ShopperStoreViewFragment fragment = new ShopperStoreViewFragment();
        Bundle args = new Bundle();
        args.putString("store_id", param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            store_id = getArguments().getString("store_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoopper_store_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        store_name = getView().findViewById(R.id.store_name);
        store_image = getView().findViewById(R.id.store_image);

        mbase = FirebaseDatabase.getInstance().getReference().child("stores").child(store_id).child("products");

        recyclerView = getView().findViewById(R.id.shopper_product_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(mbase, Product.class)
                        .build();

        adapter = new StoreProductAdapter(options);

        recyclerView.setAdapter(adapter);

        loadStoreDetails(); // Load the store details here after views are initialized
    }
    class storeViewholder extends RecyclerView.ViewHolder {
        TextView store_name, store_description;
        ImageView store_image;

        public storeViewholder(@NonNull View itemView) {
            super(itemView);
            store_name = itemView.findViewById(R.id.store_name); // Initialize TextView here
            store_description = itemView.findViewById(R.id.store_description); // Initialize TextView here
            store_image = itemView.findViewById(R.id.store_image); // Initialize ImageView here

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}