package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.shoptrack.R;
import com.example.shoptrack.data.OrderItemPlus;
import com.example.shoptrack.data.ShopperOrder;

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
        List<OrderItemPlus> orderItemList = sOrder.getOrdersList();
        adapter = new ShopperOrderAdapter(orderItemList); // Pass the cart reference here

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
}
