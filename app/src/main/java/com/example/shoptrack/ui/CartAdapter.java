package com.example.shoptrack.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Cart;
import com.example.shoptrack.data.OrderItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.OrderViewHolder> {
    private List<OrderItem> orderItemList;
    private Cart cart;

    public CartAdapter(List<OrderItem> orderItemList, Cart cart) {
        this.orderItemList = orderItemList;
        this.cart = cart;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new OrderViewHolder(view);
    }

    public void deleteItem(int position) {
        if (orderItemList.size() >1) {
            OrderItem deletedItem = orderItemList.remove(position);
            if (deletedItem != null) {
                cart.removeOrderItem(deletedItem);
                notifyItemRemoved(position);
            }
        }
        else{
            //Do a toast
        }
    }

    public void addItem(int position) {
        OrderItem addingItem = orderItemList.get(position);
        if (addingItem != null) {
            addingItem.setQuantity(addingItem.getQuantity() + 1);
            notifyItemChanged(position);
        }
    }

    public void subtractItem(int position) {
        if (orderItemList.get(position).getQuantity() > 1){
            OrderItem subtractingItem = orderItemList.get(position);
            if (subtractingItem != null) {
                subtractingItem.setQuantity(subtractingItem.getQuantity() - 1);
                notifyItemChanged(position);
            }
        }
    }


    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);
        holder.bindOrderItem(orderItem);

        holder.itemView.findViewById(R.id.deleteButtonCart).setOnClickListener(view -> {
            // Call the deleteItem method of the CartAdapter to remove the item
            deleteItem(position);
        });

        holder.itemView.findViewById(R.id.increaseButtonCart).setOnClickListener(view -> {
            // Call the addItem method of the CartAdapter to add the item
            addItem(position);
        });

        holder.itemView.findViewById(R.id.decreaseButtonCart).setOnClickListener(view -> {
            // Call the subtractItem method of the CartAdapter to subtract the item
            subtractItem(position);
        });

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
            productPriceTextView.setText(String.format("$%.2f", orderItem.getProduct().getPrice()*orderItem.getQuantity()));
            productQuantityTextView.setText(String.valueOf(orderItem.getQuantity()));
        }
    }
}
