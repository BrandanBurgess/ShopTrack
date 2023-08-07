package com.example.shoptrack.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoptrack.R;
import com.example.shoptrack.data.Cart;
import com.example.shoptrack.data.UserReference;
import com.example.shoptrack.databinding.ActivityHomeBinding;


public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Cart opening = Cart.getInstance();
        UserReference user = UserReference.getInstance();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


            if(item.getItemId() == R.id.nav_home){
                UserReference fireUser = UserReference.getInstance();
                replaceFragment(new HomeFragment());
            }
            else if (item.getItemId() == R.id.nav_cart){
                UserReference fireUser = UserReference.getInstance();
                replaceFragment(new CartFragment());
            }
            else if (item.getItemId() == R.id.nav_account){
                UserReference fireUser = UserReference.getInstance();
                replaceFragment(new AccountFragment());
            }
            else if (item.getItemId() == R.id.nav_mystore){
                UserReference fireUser = UserReference.getInstance();
                replaceFragment(new StoreFragment());
            }
            else if (item.getItemId() == R.id.nav_settings){
                UserReference fireUser = UserReference.getInstance();
                replaceFragment(new SettingsFragment());
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout,fragment);
        fragmentTransaction.commit();
    }


}
