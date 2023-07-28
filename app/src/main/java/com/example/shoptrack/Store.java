package com.example.shoptrack;

import java.util.List;

public class Store {
    private String title;
    private String description;


    // Add this constructor
    public Store(String title, String description) {
        this.title = title;
        this.description = description;

    }

    // Also, don't forget to add a no-argument constructor as it's required by Firebase
    public Store() {
    }

    // existing getters and setters ...


}
