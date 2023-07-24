package com.example.shoptrack;

public class Product {
private String name;
    private String price;
    private String quantity;
    private String image;
    private String description;
    private String category;
    private String id;

    public Product() {
    }

    public Product(String name, String price, String quantity, String image, String description, String category, String id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.category = category;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public String getId() {
        return id;
    }


}
