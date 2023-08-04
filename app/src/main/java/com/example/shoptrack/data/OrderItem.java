package com.example.shoptrack.data;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {
    public Product product;
    public int quantity;
    public boolean completed;
    public String storeID;

    public OrderItem() {
    }

    public OrderItem(Product product, int quantity, String storeID) {
        this.product = product;
        this.quantity = quantity;
        this.storeID = storeID;
        this.completed = false;
    }

    protected OrderItem(Parcel in) {
        product = in.readParcelable(Product.class.getClassLoader());
        quantity = in.readInt();
        completed = in.readByte() != 0;
        storeID = in.readString();
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void updateStatus(boolean completed) {
        this.completed = completed;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) product, flags);
        dest.writeInt(quantity);
        dest.writeByte((byte) (completed ? 1 : 0));
        dest.writeString(storeID);
    }
}