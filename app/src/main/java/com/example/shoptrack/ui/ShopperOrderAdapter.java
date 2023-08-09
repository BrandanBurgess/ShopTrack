package com.example.shoptrack.ui;


import static com.example.shoptrack.data.ShopperOrder.getStoreName;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.OrderItemPlus;
import com.example.shoptrack.data.ShopperOrder;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShopperOrderAdapter extends RecyclerView.Adapter<ShopperOrderAdapter.OrderViewHolder> {

    private FragmentManager fragmentManager;
    private List<OrderItemPlus> orderList;
    public ShopperOrder sOrder;

    public ShopperOrderAdapter(List<OrderItemPlus> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopper_order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItemPlus order = orderList.get(position);
        holder.bindOrder(order);
    }

    public static String convertTimestampToReadableTime(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void stopListening() {
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderIDTextView;

        TextView productName;
        TextView productPrice;

        TextView ProductQuantity;

        ImageView productImageView;

        ImageView statusView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderIDTextView = itemView.findViewById(R.id.orderID);
            productName = itemView.findViewById(R.id.order_product_name);
            productPrice = itemView.findViewById(R.id.shopper_order_price);
            ProductQuantity = itemView.findViewById(R.id.shopper_order_quantity);
            productImageView = itemView.findViewById(R.id.shopper_order_image);
            statusView = itemView.findViewById(R.id.status_image);

        }

        void bindOrder(OrderItemPlus order) {

            String StoreName = getStoreName(order.getStoreID());

            orderIDTextView.setText("Order ID:" + String.valueOf(order.getOrderID()));
            productName.setText(order.getProduct().getName());
            productPrice.setText(String.format("$%.2f", order.getProduct().getPrice()*order.getQuantity()));
            ProductQuantity.setText(String.format("%d",order.getQuantity()));
            Picasso.get().load(order.getProduct().getImageUrl()).into(productImageView);
            if (order.isCompleted()) {
                statusView.setImageResource(R.drawable.checkmark);
            } else {
                statusView.setImageResource(R.drawable.pending);
            }



        }


    }

}

