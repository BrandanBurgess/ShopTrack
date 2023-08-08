package com.example.shoptrack.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.shoptrack.R;

public class ShopperOrderDetailsFragment extends Fragment {

    private static final String ARG_ORDER_ID = "order_id";

    private String orderID;
    private TextView orderDetailsTextView;

    public ShopperOrderDetailsFragment() {
        // Required empty public constructor
    }

    public static ShopperOrderDetailsFragment newInstance(String orderID) {
        ShopperOrderDetailsFragment fragment = new ShopperOrderDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ORDER_ID, orderID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderID = getArguments().getString(ARG_ORDER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopper_order_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderDetailsTextView = view.findViewById(R.id.shopper_order_recycler_view);

        // Update the UI with order details
        if (orderID != null) {
            String orderDetails = "Order ID: " + orderID + "\n"; // Add more details here if needed
            orderDetailsTextView.setText(orderDetails);
        }
    }
}
