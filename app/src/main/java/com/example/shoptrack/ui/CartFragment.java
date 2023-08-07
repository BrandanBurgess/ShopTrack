package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Cart;
import com.example.shoptrack.data.Order;
import com.example.shoptrack.data.OrderItem;
import com.example.shoptrack.data.OrderWriter;
import com.example.shoptrack.data.Product;
import com.example.shoptrack.data.UserReference;

import java.util.List;

public class CartFragment extends Fragment {

    private Cart cart; // The Cart instance that contains the order items
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    public CartFragment() {
        // Required empty public constructor
    }

    public void updateTextView(String toThis, View v) {
        TextView textView = (TextView) v;
        textView.setText(toThis);
    }

    public void updateTotal(View v) {
        TextView total = (TextView) v.findViewById(R.id.totalTextView);
        total.setText("Total: $" + cart.getTotal());
        Log.d("CartFragment", "updateTotal: " + cart.getTotal());
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
        cartAdapter = new CartAdapter(orderItemList); // Pass the cart reference here

        // Set the CartAdapter to the RecyclerView
        cartRecyclerView.setAdapter(cartAdapter);


        View clearButton = view.findViewById(R.id.delete_cart);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the Cart
                cart.clearCart();
                cartAdapter.notifyDataSetChanged(); // Notify the adapter of data change
            }
        });

        //Dynamically Update total textview
//        TextView total = view.findViewById(R.id.totalTextView);
        updateTotal(view);
        cartAdapter.notifyDataSetChanged();



        View submitButton = view.findViewById(R.id.submit_cart);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Submit the Cart

                Cart cart = Cart.getInstance();
                if (cart.getsCart().size() > 0) {
                    UserReference curUser = UserReference.getInstance();
                    Order order = new Order(cart.getsCart(),curUser.getUserID());
                    OrderWriter writer = new OrderWriter();
                    writer.writeOrderToFirebase(order);
                    //refresh the UI to have the cart be empty
                    cart.clearCart();

                    cartAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Order Submitted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Cart is Empty", Toast.LENGTH_SHORT).show();
                }


            }
        });



        return view;
    }
}