package com.example.shoptrack.data;
import com.example.shoptrack.R;
import com.example.shoptrack.data.Cart;
import com.example.shoptrack.data.Order;
import com.example.shoptrack.data.OrderItem;
import com.example.shoptrack.data.OrderWriter;
import com.example.shoptrack.data.Product;
import com.example.shoptrack.data.UserReference;

import java.util.List;

public class StoreOrder {

    public List<OrderItem> orderItems;

//    public String orderItemID;

    public StoreOrder() {
    }

    public StoreOrder(List<OrderItem> orderItems, String orderItemID){
        this.orderItems = orderItems;
//        this.orderItemID = orderItemID;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

//    public String getOrderItemID() {
//        return orderItemID;
//    }

//    public void setOrderItemID(String orderItemID) {
//        this.orderItemID = orderItemID;
//    }

}
