package com.example.shoptrack;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoptrack.databinding.ActivityHomeBinding;


public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


            if(item.getItemId() == R.id.nav_home){
                replaceFragment(new HomeFragment());
            }
            else if (item.getItemId() == R.id.nav_cart){
                replaceFragment(new CartFragment());
            }
            else if (item.getItemId() == R.id.nav_account){
                replaceFragment(new AccountFragment());
            }
            else if (item.getItemId() == R.id.nav_mystore){
                replaceFragment(new StoreFragment());
            }
            else if (item.getItemId() == R.id.nav_settings){
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
