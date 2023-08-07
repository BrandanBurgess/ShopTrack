package com.example.shoptrack.data;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class OrderWriter {
    public void writeOrderToFirebase(Order order) {
        // Generate a new key for the order
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");
        DatabaseReference newOrderRef = ordersRef.push();
        String orderId = newOrderRef.getKey();

        // Write the order details to the new order node
        newOrderRef.child("userID").setValue(order.getUserID());

        // Write the orderItems to the orderItems child node
        DatabaseReference orderItemsRef = newOrderRef.child("orderItems");
        for (OrderItem orderItem : order.getOrder()) {
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
        }
    }
}
