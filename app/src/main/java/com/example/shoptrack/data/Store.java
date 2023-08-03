package com.example.shoptrack.data;

import java.util.List;

public class Store {
    public String title;
    public String description;
    public String imageUrl;

    // Add this constructor
    public Store(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Also, don't forget to add a no-argument constructor as it's required by Firebase
    public Store() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
