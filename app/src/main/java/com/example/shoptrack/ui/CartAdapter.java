package com.example.shoptrack.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.OrderItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.OrderViewHolder> {
    private List<OrderItem> orderItemList;

    public CartAdapter(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);
        holder.bindOrderItem(orderItem);
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productPriceTextView;
        TextView productQuantityTextView;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
        }

        void bindOrderItem(OrderItem orderItem) {
            productNameTextView.setText(orderItem.getProduct().getName());
            productPriceTextView.setText(String.format("$%.2f", orderItem.getProduct().getPrice()));
            productQuantityTextView.setText(String.valueOf(orderItem.getQuantity()));
        }
    }
}


