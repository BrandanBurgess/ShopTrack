package com.example.shoptrack.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.shoptrack.R;
import com.example.shoptrack.data.Store;
public class HomeFragment extends Fragment implements StoreAdapter.onItemClickListener {

    public RecyclerView recyclerView;
    StoreAdapter adapter;
    DatabaseReference mbase;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mbase = FirebaseDatabase.getInstance().getReference().child("stores");

        recyclerView = getView().findViewById(R.id.store_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FirebaseRecyclerOptions<Store> options =
                new FirebaseRecyclerOptions.Builder<Store>()
                        .setQuery(mbase, Store.class)
                        .build();

        adapter = new StoreAdapter(options);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }


    //implement the interface method
    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
        //get the store id
        String store_id = adapter.getRef(position).getKey();
        //create a new instance of the fragment
        ShopperStoreViewFragment shopperStoreViewFragment = ShopperStoreViewFragment.newInstance(store_id);
        //replace the current fragment with the new fragment
        replaceFragment(shopperStoreViewFragment);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}