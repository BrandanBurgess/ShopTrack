package com.example.shoptrack.data;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Product implements Parcelable {
    public String name;
    public double price;
    public String image;
    public String description;
    public String storeId;
    public int quantity;

    public Product() {
    }

    public Product(String name, double price, String image, String description, String storeId, int quantity) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.storeId = storeId;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){ this.name = name; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }
    public String getStoreId(){ return storeId; }
    public int getQuantity() { return quantity; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// Non-Basic Functionality /////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void incrementQuantity() {
        quantity++;
    }

    public boolean decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }

    public static int getIndexById(String storeId, ArrayList<Product> products) {
        if (products != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getStoreId().equals(storeId)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static ArrayList<Product> incrementProduct(Product product, ArrayList<Product> products) {
        int i = Product.getIndexById(product.getStoreId(), products);

        if (i > -1) {
            Product inc = products.get(i);
            inc.incrementQuantity();
            products.set(i, inc);
        } else {
            product.quantity = 1;
            products.add(product);
        }

        return products;
    }

    public static ArrayList<Product> decrementProduct(Product product, ArrayList<Product> products) {
        int i = Product.getIndexById(product.getStoreId(), products);

        if (i > -1) {
            Product inc = products.get(i);
            inc.decrementQuantity();
            products.set(i, inc);
        }

        return products;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Parcel Necessities Below ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    protected Product(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        image = in.readString();
        description = in.readString();
        storeId = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(storeId);
        dest.writeInt(quantity);
    }
}
