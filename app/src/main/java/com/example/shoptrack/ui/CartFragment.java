package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Cart;
import com.example.shoptrack.data.OrderItem;
import com.example.shoptrack.data.Product;

import java.util.List;

public class CartFragment extends Fragment {

    private Cart cart; // The Cart instance that contains the order items
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize the RecyclerView and set its layout manager
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cartRecyclerView.setLayoutManager(layoutManager);

        // Create an instance of the CartAdapter with the order items from the Cart
        cart = Cart.getInstance(); // Assuming Cart is implemented as a singleton
        List<OrderItem> orderItemList = cart.getsCart();
        cartAdapter = new CartAdapter(orderItemList);

        // Set the CartAdapter to the RecyclerView
        cartRecyclerView.setAdapter(cartAdapter);

        cartRecyclerView.setAdapter(cartAdapter);

        View addButton = view.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new OrderItem with product, quantity 0, and storeID = 1 to the Cart
                Product newProduct = new Product("New Product", 0.0, "yo", "1", "2");
                OrderItem newOrderItem = new OrderItem(newProduct, 0, "1");
                cart.addOrderItem(newOrderItem);
                cartAdapter.notifyDataSetChanged(); // Notify the adapter of data change
            }
        });

        return view;
    }
}