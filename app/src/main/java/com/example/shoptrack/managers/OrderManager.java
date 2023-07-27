package com.example.shoptrack.managers;

import com.example.shoptrack.data.Order;

public interface OrderManager {
    void addOrder(Order order);
    void removeOrder(Order order);
    void updateOrder(Order order);
    void getOrder(Order order);
    void getAllOrders();


}
