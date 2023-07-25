package com.example.shoptrack;

import java.util.List;

public class Store {
    private String title;
    private String description;

    public Store() {
    }

    public Store(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
