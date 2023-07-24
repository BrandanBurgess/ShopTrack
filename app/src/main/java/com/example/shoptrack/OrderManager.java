package com.example.shoptrack;

public interface OrderManager {
    void addOrder(Order order);
    void removeOrder(Order order);
    void updateOrder(Order order);
    void getOrder(Order order);
    void getAllOrders();


}
