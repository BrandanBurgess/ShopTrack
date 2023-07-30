package com.example.shoptrack.data;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    public HashMap<Order, String> orderToCustomer;
    public HashMap<Store, Product> storeToProduct;
    public boolean isComplete;

    public Order() {
        orderToCustomer = new HashMap<>();
        storeToProduct = new HashMap<>();
        isComplete = false;
    }

    /**
     * Adds an item to the Hashmap, storeToProduct.
     *
     * @param store the store to add
     * @param product the product to add
     */
    public void addItem(Store store, Product product) {
        storeToProduct.put(store, product);
    }

    /**
     * Removes an item from the Hashmap, storeToProduct.
     *
     * @param store the store to remove (along with the product)
     */
    public void removeItem(Store store) {
        storeToProduct.remove(store);
    }

    /**
     * Checks if an order is complete.
     *
     * @return a boolean corresponding to the status of the order
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Marks an order as complete.
     */
    public void markComplete() {
        isComplete = true;
    }
}
