package com.example.shoptrack.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoptrack.R;
import com.example.shoptrack.data.ShopperOrder;
import com.example.shoptrack.data.UserReference;
import com.example.shoptrack.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    Button logout_btn;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView userDisplay = view.findViewById(R.id.hello_user);

        UserReference user = UserReference.getInstance();
        String userName = user.getOur_email();
        userDisplay.setText("Hello" + "\n" + userName);

        logout_btn = view.findViewById(R.id.logout_btn);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserReference.instance1 = null;
                ShopperOrder.instance3 = null;
                FirebaseAuth.getInstance().signOut();

                // After logging out, redirect to MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}