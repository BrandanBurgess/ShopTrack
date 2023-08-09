package com.example.shoptrack.ui;

import com.example.shoptrack.data.Product;

public interface ProductActionListener {
    void onEditProduct(Product product);
    void onDeleteProduct(Product product);
}
