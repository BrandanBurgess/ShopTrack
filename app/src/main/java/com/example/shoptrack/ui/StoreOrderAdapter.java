package com.example.shoptrack.ui;
import android.util.Log;
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
import com.example.shoptrack.data.Order;
import com.example.shoptrack.data.OrderItem;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.StoreOrder;
import com.example.shoptrack.data.UserReference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class StoreOrderAdapter extends FirebaseRecyclerAdapter <OrderItem, StoreOrderAdapter.orderItemViewholder>{

    private FragmentManager fragmentManager;

    public StoreOrderAdapter(@NonNull FirebaseRecyclerOptions<OrderItem> options, FragmentManager fragmentManager) {
        super(options);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onBindViewHolder(@NonNull orderItemViewholder holder, int position, @NonNull OrderItem model){

        holder.order_name.setText(model.product.name);
        holder.order_ID.setText(model.product.productID);
        Picasso.get().load(model.product.imageUrl).into(holder.order_image);

    }


    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getRef().removeValue();
    }




    @NonNull
    @Override
    public orderItemViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order, parent, false);

        return new StoreOrderAdapter.orderItemViewholder(view);
    }

    class orderItemViewholder extends RecyclerView.ViewHolder{
        TextView order_name, order_ID;

        ImageView order_image;

        public orderItemViewholder(@NonNull View itemView){
            super(itemView);
            order_name = itemView.findViewById(R.id.order_name);
            order_ID = itemView.findViewById(R.id.order_ID);
            order_image = itemView.findViewById(R.id.order_image);

        }
    }


}
