package com.example.shoptrack.data;

import android.os.Parcel;
import android.os.Parcelable;
//import all firebase related imports
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.shoptrack.R;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.Product;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class OrderItemPlus implements Parcelable {
    public Product product;
    public int quantity;
    public boolean completed;
    public String storeID;

    public String orderID;

    public long timestamp;




    public OrderItemPlus() {
    }

    public OrderItemPlus(Product product, int quantity, String storeID) {
        this.product = product;
        this.quantity = quantity;
        this.storeID = storeID;
        this.completed = false;
    }

    public OrderItemPlus(OrderItem orderItem, long timestamp, String orderID){
        this.product = orderItem.product;
        this.quantity = orderItem.quantity;
        this.storeID = orderItem.storeID;
        this.completed = orderItem.completed;
        this.timestamp = timestamp;
        this.orderID = orderID;
    }

    protected OrderItemPlus(Parcel in) {
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

    public void setProduct(Product product) {
        this.product = product;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return product.equals(orderItem.product) &&
                storeID.equals(orderItem.storeID);
    }



    public String getStoreID() {
        return storeID;
    }


    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setCompletion(boolean completion) {
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static String convertTimestampToReadable(long timestampMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(timestampMillis);
        return sdf.format(date);
    }

    public boolean isCompleted() {
        return this.completed;
    }
}