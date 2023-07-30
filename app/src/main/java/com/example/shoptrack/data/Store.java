package com.example.shoptrack.data;

import java.util.List;

public class Store {
    public String name;
    public String description;
    public String imageUrl;

    // Add this constructor
    public Store(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Also, don't forget to add a no-argument constructor as it's required by Firebase
    public Store() {
    }

    public String getName(){
        return this.name;
    }
}
