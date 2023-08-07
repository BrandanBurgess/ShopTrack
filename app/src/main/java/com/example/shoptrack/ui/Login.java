package com.example.shoptrack.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoptrack.R;
import com.example.shoptrack.firebase.FirebaseUserManager;
import com.example.shoptrack.utils.DBConnection;
import com.example.shoptrack.utils.DBUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView textView;
    FirebaseUserManager userManager = DBConnection.getInstance().getUserManager();

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = DBUtil.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.signup_btn_signup);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                progressBar.setVisibility(View.VISIBLE);
                String email,password;
                email = editTextEmail.getText().toString();
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                userManager.logIn(email, password, result -> {
                    result.onSuccess(firebaseUser -> {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Login Successful",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();
                    }).onFailure(throwable -> {
                        // maybe you want the progress to be gone once you've invalidated this?
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT)
                                .show();
                    });

                });
            }
        });
    }
}