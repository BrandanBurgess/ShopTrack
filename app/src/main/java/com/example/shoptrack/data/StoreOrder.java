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

    List<OrderItem> orderItems;

    public StoreOrder() {
    }

    public StoreOrder(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
