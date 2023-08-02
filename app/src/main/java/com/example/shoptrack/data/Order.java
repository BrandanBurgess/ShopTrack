package com.example.shoptrack.data;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Order implements Parcelable {
    private double total;
    private ArrayList<Product> products = new ArrayList<Product>();
    private String createdAt;
    private boolean isCompleted;
    private String id;
    private String storeId;
    private String customerId;
    private String customerName;

    public Order() {
    }

    // For parsing orders (Everything, EXCEPT the Products) //
    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Order(Parcel in) {
        total = in.readDouble();
        createdAt = in.readString();
        isCompleted = in.readBoolean();
        id = in.readString();
        storeId = in.readString();
        customerId = in.readString();
        customerName = in.readString();

    }

    // Don't care about what this function does. Just needs to be there to satisfy the ParcelInterface //
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(total);
        dest.writeString(createdAt);
        dest.writeBoolean(isCompleted);
        dest.writeString(id);
        dest.writeString(storeId);
        dest.writeString(customerId);
        dest.writeString(customerName);
    }

}