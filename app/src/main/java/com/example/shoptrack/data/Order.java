package com.example.shoptrack.data;

import java.util.List;

public class Order {
    private List<OrderItem> order;
    private String userID;


    public Order() {
    }

    public Order (List<OrderItem> order, String userID) {
        this.order = order;
        this.userID = userID;
    }

    public List<OrderItem> getOrder() {
        return order;
    }

    public void setOrder(List<OrderItem> order) {
        this.order = order;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        int quantity = 0;
        for (OrderItem item : order) {
            quantity += item.getQuantity();
        }
        return quantity;
    }
}


