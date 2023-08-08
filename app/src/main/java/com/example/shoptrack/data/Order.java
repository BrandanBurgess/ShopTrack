package com.example.shoptrack.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    public List<OrderItem> orderItems;

    public long timestamp;
    public String userID;

    public String orderID;


    public Order() {
    }

    public Order (List<OrderItem> order, String userID) {
        this.orderItems = order;
        this.userID = userID;
    }

    public List<OrderItem> getOrder() {
        return orderItems;
    }

    public void setOrder(List<OrderItem> order) {
        this.orderItems = order;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public List<String> getStoreIDs() {
        List<String> storeIDs = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            storeIDs.add(orderItem.getStoreID());
            }
        return storeIDs;
        }

    public String makeOrderIds() {
        String orderIds = "";
        for (OrderItem orderItem : orderItems) {
            orderIds += orderItem.getStoreID() + " ";
        }
        return orderIds;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }




}



