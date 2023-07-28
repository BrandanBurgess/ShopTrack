package com.example.shoptrack.managers;

import com.example.shoptrack.data.Product;
import com.example.shoptrack.ui.StoreFragment;

public interface ProductManager {
    void addProduct(StoreFragment store, Product product);
    void removeProduct(StoreFragment store, Product product);
    void updateProduct(StoreFragment store, Product product);
    void getProduct(StoreFragment store, Product product);
    void getAllProducts(StoreFragment store);
}
