package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Product;
import com.example.shoptrack.data.Store;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ShopperStoreViewFragment extends Fragment {

    public RecyclerView recyclerView;

    StoreProductAdapter adapter;
    DatabaseReference mbase;

    public String store_id;

    TextView shopper_store_name;
    ImageView shopper_store_image;


    public ShopperStoreViewFragment() {
        // Required empty public constructor
    }

    public static ShopperStoreViewFragment newInstance(String store_id) {
        ShopperStoreViewFragment fragment = new ShopperStoreViewFragment();
        Bundle args = new Bundle();
        args.putString("store_id", store_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            store_id = getArguments().getString("store_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoopper_store_view, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mbase = FirebaseDatabase.getInstance().getReference().child("stores").child(store_id).child("products");

        recyclerView = getView().findViewById(R.id.shopper_product_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(mbase, Product.class)
                        .build();

        adapter = new StoreProductAdapter(options);

        recyclerView.setAdapter(adapter);

        shopper_store_name = getView().findViewById(R.id.shopper_store_name);
        shopper_store_image = getView().findViewById(R.id.shopper_store_logo);

        mbase.getParent().child(store_id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Store store = task.getResult().getValue(Store.class);
                shopper_store_name.setText(store.title);
                Picasso.get().load(store.imageUrl).into(shopper_store_image);
            } else {
                // Handle failures
                // ...
            }
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}