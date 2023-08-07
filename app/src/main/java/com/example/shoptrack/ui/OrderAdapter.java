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
import com.example.shoptrack.data.UserReference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class OrderAdapter extends FirebaseRecyclerAdapter <Order, OrderAdapter.orderViewholder>{

    private FragmentManager fragmentManager;

    public OrderAdapter(@NonNull FirebaseRecyclerOptions<Order> options, FragmentManager fragmentManager) {
        super(options);
        this.fragmentManager = fragmentManager;
    }

    @Override

    protected void onBindViewHolder(@NonNull orderViewholder holder, int position, @NonNull Order model){





//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

    }

//    private void replaceFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.owner_orders_frame, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//    }

    @NonNull
    @Override
    public orderViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order, parent, false);

        return new OrderAdapter.orderViewholder(view);
    }

    class orderViewholder extends RecyclerView.ViewHolder{
        TextView order_name, order_quantity;

        public orderViewholder(@NonNull View itemView){
            super(itemView);
            order_name = itemView.findViewById(R.id.order_name);

        }
    }


}
