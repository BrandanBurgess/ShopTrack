package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.shoptrack.R;
import com.example.shoptrack.data.OrderItemPlus;
import com.example.shoptrack.data.ShopperOrder;
import com.example.shoptrack.data.UserReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    private RecyclerView recyclerView;
    private ShopperOrderAdapter adapter;

    private ShopperOrder sOrder;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);


        // Initialize the RecyclerView and set its layout manager
        recyclerView = view.findViewById(R.id.shopper_order_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        sOrder = ShopperOrder.getInstance(); // Assuming ShopperOrder is implemented as a singleton


        List<OrderItemPlus> ordersList = sOrder.getOrdersList();
        // Initialize the CartAdapter for RecyclerView
        adapter = new ShopperOrderAdapter(ordersList); // Pass the cart reference here

        // Set the CartAdapter to the RecyclerView
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged(); // Notify the adapter that the data may have changed
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening(); // Stop listening for changes
    }

    public void setArray(List<OrderItemPlus> destList, List<OrderItemPlus> sourceList) {
        destList.clear();
        for (OrderItemPlus order : sourceList) {
            destList.add(order);
        }
    }
}
