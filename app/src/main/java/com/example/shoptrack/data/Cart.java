package com.example.shoptrack.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Parcelable {
    private List<OrderItem> sCart;

    // Singleton instance
    public static Cart instance;

    private Cart() {
        sCart = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void makeEqual(Cart cart) {
        sCart = cart.getsCart();
    }

    public void addOrderItem(OrderItem orderItem) {
        if (sCart.contains(orderItem)) {
            int index = sCart.indexOf(orderItem);
            OrderItem existingOrderItem = sCart.get(index);
            existingOrderItem.updateQuantity(existingOrderItem.getQuantity() + 1);
            return;
        }
        else{
            sCart.add(orderItem);
        }

    }

    public void removeOrderItem(OrderItem orderItem) {
        sCart.remove(orderItem);
    }

    public void clearCart() {
        sCart.clear();
        this.getTotal();
    }

    public void updateOrderItemQuantity(OrderItem orderItem, int quantity) {
        orderItem.updateQuantity(quantity);
    }

    public List<OrderItem> getsCart() {
        return sCart;
    }

    // Parcelable implementation
    protected Cart(Parcel in) {
        sCart = in.createTypedArrayList(OrderItem.CREATOR);
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(sCart);
    }

    public double getTotal(){
        double total = 0;
        for(OrderItem orderItem : sCart){
            total += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        return total;
    }
}