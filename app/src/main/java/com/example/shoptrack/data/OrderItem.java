package com.example.shoptrack.data;

public class OrderItem {
    public Product product;
    public int quantity;
    public boolean completed;
    public String storeID;


    public OrderItem() {
    }

    public OrderItem(Product product, int quantity, String storeID,) {
        this.product = product;
        this.quantity = quantity;
        this.storeID = storeID;
        this.completed = false;
    }
    public void updateQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void updateStatus(boolean completed) {

        this.completed = completed;
    }
}
