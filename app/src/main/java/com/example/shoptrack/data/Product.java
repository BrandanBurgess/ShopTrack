package com.example.shoptrack.data;

public class Product {


    public String name;
    public Double price;
    public String description;
    public String imageUrl;
    public String ownerId;

    public String productID;

    public Product() {
    }

    public Product(String name, Double price, String description, String imageUrl, String ownerId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ownerId = ownerId;
        this.productID = "";
    }

    // getters
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOwnerId() {
        return ownerId;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
