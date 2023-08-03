package com.example.shoptrack.data;

import android.media.Image;

public class Product {
    public String name;
    public double price;
    public String image;
    public String description;

    public Product() {
    }

    public Product(String name, double price, int quantity, String image, String description, String category, String id) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
