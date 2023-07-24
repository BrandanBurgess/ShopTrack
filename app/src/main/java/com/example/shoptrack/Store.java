package com.example.shoptrack;

import java.util.List;

public class Store {
    private String name;
    private String description;
    private String ownerId; //the id of the store owner
    private List<Product> products;

    public Store() {
    }

    public Store(String name, String description, String ownerId, List<Product> products) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}