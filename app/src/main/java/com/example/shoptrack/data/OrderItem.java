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


// Note: The author of this code is Sean (SheeperGit). See Sean's_Order_Implementation branch. //

public class OrderItem implements Parcelable {
    public Product product;
    public int quantity;
    public boolean completed;
    public String storeID;
    public String productID;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return product.equals(orderItem.product) &&
                storeID.equals(orderItem.storeID);
    }

    public String getProductID() {


        return productID;
    }
}