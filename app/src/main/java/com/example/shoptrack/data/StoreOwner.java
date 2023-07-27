package com.example.shoptrack.data;

import com.example.shoptrack.managers.AccountManager;
import com.example.shoptrack.managers.OrderManager;
import com.example.shoptrack.managers.ProductManager;

public abstract class StoreOwner extends User implements AccountManager, OrderManager,
        ProductManager {

    public StoreOwner(String email, String role) {
        super(email, role);
    }

    public void addProduct(Product product) {
        // TODO implement here
    }

    public void removeProduct(Product product) {
        // TODO implement here
    }

    public void updateProduct(Product product) {
        // TODO implement here
    }

    public void getProduct(Product product) {
        // TODO implement here
    }

    public void getAllProducts() {
        // TODO implement here
    }

    public void addOrder(Order order) {
        // TODO implement here
    }

    public void removeOrder(Order order) {
        // TODO implement here
    }

    public void updateOrder(Order order) {
        // TODO implement here
    }

    public void getOrder(Order order) {
        // TODO implement here
    }

    public void getAllOrders() {
        // TODO implement here
    }


}
