package com.example.shoptrack.data;

import java.util.List;

public class Store {
    private String title;
    private String description;
    private String imageUrl;

    // Add this constructor
    public Store(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Also, don't forget to add a no-argument constructor as it's required by Firebase
    public Store() {
    }

    // existing getters and setters ...

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
