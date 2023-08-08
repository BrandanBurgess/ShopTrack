package com.example.shoptrack.data;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.List;


public class OrderWriter {

    public List<String> orderIDs = new ArrayList<String>();

    public void writeOrderToFirebase(Order order) {
        // Generate a new key for the order
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");
        DatabaseReference newOrderRef = ordersRef.push();
        String orderId = newOrderRef.getKey();



        //write list of StoreIDs to the new order node
//        newOrderRef.child("storeIDs").setValue(order.getStoreIDs());
        newOrderRef.child("userID").setValue(order.getUserID());
        // Add a timestamp field using ServerValue.TIMESTAMP
        long timestamp = ServerValue.TIMESTAMP.size();
        newOrderRef.child("timestamp").setValue(ServerValue.TIMESTAMP);
        newOrderRef.child("orderID").setValue(orderId);

        String userID = order.getUserID();




        // Write the orderItems to the orderItems child node
        DatabaseReference orderItemsRef = newOrderRef.child("orderItems");
        for (OrderItem orderItem : order.getOrder()) {
            DatabaseReference orderItemToStore = FirebaseDatabase.getInstance().getReference().child("stores").child(orderItem.getStoreID());


            DatabaseReference newOrderItemRef = orderItemsRef.push();
            String orderItemId = newOrderItemRef.getKey();




            // Write the orderItem details to the new orderItem node
            newOrderItemRef.child("completed").setValue(orderItem.completed);
            newOrderItemRef.child("quantity").setValue(orderItem.quantity);
            newOrderItemRef.child("storeID").setValue(orderItem.storeID);

            // Write the product details to the new product node under orderItem
            DatabaseReference productRef = newOrderItemRef.child("product");
            productRef.child("name").setValue(orderItem.product.name);
            productRef.child("price").setValue(orderItem.product.price);
            productRef.child("description").setValue(orderItem.product.description);
            productRef.child("imageUrl").setValue(orderItem.product.imageUrl);
            productRef.child("ownerId").setValue(orderItem.product.ownerId);
            productRef.child("productID").setValue(orderItem.product.productID);

            //StoreOrder
            DatabaseReference StoreOrder = FirebaseDatabase.getInstance().getReference().child("StoreOrders");
            //make each storeID for each orderItem a child of StoreOrder
            DatabaseReference newStoreOrderRef = StoreOrder.child(orderItem.getStoreID());
            //write the orderItems from Order to the newStoreOrderRef
            newStoreOrderRef.child(orderItemId).setValue(orderItem);
            //write the orderID to the newStoreOrderRef
            Log.d("ORDERID", orderId);
            newStoreOrderRef.child(orderItemId).child("orderID").setValue(orderId);


            OrderItemPlus orderItemPlus = new OrderItemPlus(orderItem,timestamp, orderId);
            //UserOrder (within User)
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userID);
            DatabaseReference userOrdersRef = userRef.child("userOrders");
            //Create new key for the orderItem
            DatabaseReference newUserOrderRef = userOrdersRef.push();
            //Write the orderItemPlus from Order to the newUserOrderRef
            newUserOrderRef.setValue(orderItemPlus);

        }
    }



}
