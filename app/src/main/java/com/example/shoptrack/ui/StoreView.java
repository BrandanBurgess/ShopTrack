package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.shoptrack.R;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.Product;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class StoreView extends Fragment {

    public RecyclerView recyclerView;
    StoreProductAdapter adapter;
    DatabaseReference mbase;

    TextView shopper_store_name;

    ImageView shopper_store_logo;

    static String storeId_name;

    public StoreView() {
        // Required empty public constructor
    }

    public static StoreView newInstance(String storeId) {
        StoreView fragment = new StoreView();
        Bundle args = new Bundle();
        args.putString(storeId_name, storeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoopper_store_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mbase = FirebaseDatabase.getInstance().getReference().child("stores");


        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(getArguments().getString(storeId_name)).child("title").getValue() == null){
                    Toast.makeText(getContext(), "Store not found", Toast.LENGTH_LONG).show();
                    return;
                }
                shopper_store_name.setText(snapshot.child(getArguments().getString(storeId_name)).child("title").getValue().toString());
                if (snapshot.child(getArguments().getString(storeId_name)).child("imageUrl").getValue() != null){
                    Picasso.get().load(snapshot.child(getArguments().getString(storeId_name)).child("imageUrl").getValue().toString()).into(shopper_store_logo);
                    return;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = getView().findViewById(R.id.shopper_product_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");

        Query query = productsRef.orderByChild("ownerId").equalTo(getArguments().getString(storeId_name));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, Product.class)
                        .build();

        adapter = new StoreProductAdapter(options);

        recyclerView.setAdapter(adapter);


        //set the store name and logo
        shopper_store_name = getView().findViewById(R.id.shopper_store_name);
        shopper_store_logo = getView().findViewById(R.id.shopper_store_logo);
    }
    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
