package com.example.shoptrack;

public interface StoreManager {
    void addStore(Store store);
    void removeStore(Store store);
    void updateStore(Store store);
    void getStore(Store store);
    void getAllStores();
}
