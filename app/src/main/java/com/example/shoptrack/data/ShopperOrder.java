package com.example.shoptrack.data;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;

import com.example.shoptrack.ui.ShopperOrderAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ShopperOrder {
    public List<OrderItemPlus> ordersList;


    // Singleton instance
    public static ShopperOrder instance3;

    public ShopperOrder() {
        UserReference user = UserReference.getInstance();
        String uID = user.getUserID();

        DatabaseReference fd = FirebaseDatabase.getInstance().getReference().child("users").child(uID);

        DatabaseReference userOrdersRef = fd.child("userOrders");

        userOrdersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<OrderItemPlus> orders = new ArrayList<>();
                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    OrderItemPlus order = orderSnapshot.getValue(OrderItemPlus.class);
                    orders.add(order);
                }
                ordersList = orders;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public static ShopperOrder getInstance(){
        if (instance3 == null) {
            instance3 = new ShopperOrder();
        }
        return instance3;
    }

    public ShopperOrder(List<OrderItemPlus> ordersList) {
        this.ordersList = ordersList;
    }

    public List<OrderItemPlus> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrderItemPlus> ordersList) {
        this.ordersList = ordersList;
    }

    public static String convertTimestampToReadableTime(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void passString(String storeID, String found){
        storeID = found;
    }

    public static String getStoreName(String storeID){
        DatabaseReference fd = FirebaseDatabase.getInstance().getReference().child("stores").child(storeID);
        DatabaseReference storeNameRef = fd.child("title");

        String storeName1 = "";

        storeNameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storeName = dataSnapshot.getValue(String.class);
                    passString(storeName1, storeName);
                } else {
                    // Store name not found or doesn't exist
                    Log.d("ShopperOrder", "Store name not found or doesn't exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that may occur while retrieving data
                System.out.println("Error: " + databaseError.getMessage());
            }
        });

        return storeName1;
    }

}
