package com.example.shoptrack.managers;

import com.example.shoptrack.data.Order;
import com.example.shoptrack.data.Product;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface OrderManager {
    void addOrder(Order order);
    void removeOrder(Order order);
    void updateOrder(Order order);
    void getOrder(Order order);
    void getAllOrders();

    /* Pseudo-code for addOrder (Warning: Not working!)
    public static void addOrder(ArrayList<Order> orderData) {
        for (Order nextOrder : orderData) {
            FirebaseDatabase.addOrder(nextOrder.getStoreName(store), nextOrder.getProductName(), nextOrder.getCustomerName(), nextOrder);
        }
    }
     */


    /**
     * Adds the order to the database; returns a boolean indicating if adding the order was
     * successful.
     *
     * @param storeName the name of the store
     * @param productName the name of the product
     * @param customerName the name of the customer
     * @param order the order to be placed
     * @return a boolean indicating if the order was added to the db successfully
     */
    /* Pseudo-code for (Incremental) addOrder (Warning: Not working!)
    public static boolean addOrder(String storeName, String productName, String customerName, Order order) {
        if (!(FirebaseDatabase.containsOrder(order))) {
            FirebaseDatabase.storeToProduct.put(storeName, productName);
            FirebaseDatabase.orderToCustomer.put(order, customerName);
            return true;
        } else {
            return false;
        }
    }
     */


    /**
     * Checks if a store contains the given product.
     *
     * @param product the product to check
     * @return a boolean corresponding to the existence of the product in the store.
     */
    /* Pseudo-code for containsProduct (Warning: Not working!)
    public static boolean containsProduct(Product product) {
        return storeToProduct.containsValue(product);
    }
     */
}
