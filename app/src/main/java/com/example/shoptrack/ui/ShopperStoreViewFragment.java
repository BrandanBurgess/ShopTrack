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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopperStoreViewFragment extends Fragment {

    public RecyclerView recyclerView;

    StoreProductAdapter adapter;
    DatabaseReference mbase;

    TextView store_name;
    ImageView store_image;




    public ShopperStoreViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoopper_store_view, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mbase = FirebaseDatabase.getInstance().getReference().child("products");

        recyclerView = getView().findViewById(R.id.shopper_product_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // only populate recycler view with products from the store that was clicked on.
        // get store name from the store that was clicked on.






}