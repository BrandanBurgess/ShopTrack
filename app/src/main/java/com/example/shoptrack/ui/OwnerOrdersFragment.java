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

import com.example.shoptrack.data.Cart;
import com.example.shoptrack.data.Order;
import com.example.shoptrack.data.OrderItem;
import com.example.shoptrack.data.OrderWriter;
import com.example.shoptrack.data.StoreOrder;
import com.example.shoptrack.data.UserReference;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.shoptrack.R;


public class OwnerOrdersFragment extends Fragment {

    public RecyclerView recyclerView;

    public StoreOrderAdapter adapter;

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



        recyclerView = getView().findViewById(R.id.ordersRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        storeID = getArguments().getString("storeID");

        //make a query to get all OrderItems that are in the branch of the database StoreOrders->storeID->each key is an orderItem
        mbase = FirebaseDatabase.getInstance().getReference().child("StoreOrders").child(storeID);

        //check if the query is empty
        mbase.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    Toast.makeText(getContext(), "No orders yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //make a firebaserecycleroption to get all the orderItems from the query above and pass them to the adapter
        FirebaseRecyclerOptions<OrderItem> options = new FirebaseRecyclerOptions.Builder<OrderItem>()
                .setQuery(mbase, OrderItem.class)
                .build();

        //make a new adapter and pass the options to it
        adapter = new StoreOrderAdapter(options, getParentFragmentManager());

        //set the adapter to the recyclerview
        recyclerView.setAdapter(adapter);


        View fulfillbutton = getView().findViewById(R.id.fulfill_all_button);

        fulfillbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //go through all the orderItems in the adapter and set their completed value to true
                DatabaseReference orderItemsRef =  FirebaseDatabase.getInstance().getReference().child("StoreOrders").child(storeID);

                for(int i = 0; i < adapter.getItemCount(); i++){
                    OrderItem orderItem = adapter.getItem(i);
                    if (orderItem != null){
                        orderItem.completed = true;
                        //get the key of the orderItem
                        String key = adapter.getRef(i).getKey();
                        //update the orderItem in the database
                        orderItemsRef.child(key).setValue(orderItem);
                        adapter.deleteItem(i);

                    }
                }
            }
        });


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