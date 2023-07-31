package com.example.shoptrack.data;

import java.util.List;
import java.util.ArrayList;


public class Cart {
    private List<OrderItem> sCart;

    public Cart() {
        sCart = new ArrayList<>();
    }

    public void addOrderItem(OrderItem orderItem) {

        sCart.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {

        sCart.remove(orderItem);
    }

    public void updateOrderItemQuantity(OrderItem orderItem, int quantity) {
        orderItem.updateQuantity(quantity);
    }
    public List<OrderItem> getsCart() {

        return sCart;
    }
}
