package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoptrack.data.Order;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.shoptrack.R;


public class OwnerOrdersFragment extends Fragment {

    public RecyclerView recyclerView;

    public OrderAdapter adapter;

    DatabaseReference mbase;

    public static String storeID;

    public OwnerOrdersFragment() {
    }

    public static OwnerOrdersFragment newInstance(String storeId) {
        OwnerOrdersFragment fragment = new OwnerOrdersFragment();
        Bundle args = new Bundle();
        args.putString("storeID", storeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_orders, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mbase = FirebaseDatabase.getInstance().getReference().child("orders");

        recyclerView = getView().findViewById(R.id.ordersRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        storeID = getArguments().getString("storeID");

        Log.d("storeID", storeID);


        //query to check if storeID contains storeID
        Query query = mbase;
        //check if query is empty


//        FirebaseRecyclerOptions<Order> options =
//                new FirebaseRecyclerOptions.Builder<Order>()
//                        .setQuery(query, Order.class)
//                        .build();
//
//
//        adapter = new OrderAdapter(options, getParentFragmentManager());
//
//        recyclerView.setAdapter(adapter);
//

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