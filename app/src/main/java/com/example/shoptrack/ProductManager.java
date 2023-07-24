package com.example.shoptrack;

public interface ProductManager {
    void addProduct(StoreFragment store, Product product);
    void removeProduct(StoreFragment store, Product product);
    void updateProduct(StoreFragment store, Product product);
    void getProduct(StoreFragment store, Product product);
    void getAllProducts(StoreFragment store);
}
