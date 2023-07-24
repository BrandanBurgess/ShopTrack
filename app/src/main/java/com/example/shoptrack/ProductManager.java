package com.example.shoptrack;

public interface ProductManager {
    void addProduct(Store store, Product product);
    void removeProduct(Store store, Product product);
    void updateProduct(Store store, Product product);
    void getProduct(Store store, Product product);
    void getAllProducts(Store store);
}
