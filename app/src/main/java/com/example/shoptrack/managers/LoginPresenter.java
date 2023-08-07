package com.example.shoptrack.managers;

import android.content.Context;
import android.view.View;

import com.example.shoptrack.Contract;
import com.example.shoptrack.R;
import com.example.shoptrack.ui.Login;
import com.example.shoptrack.data.Shopper;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements Contract.Presenter {

    private Contract.Model auth;
    private Login view;

    public LoginPresenter(Contract.Model auth, Login view) {
        this.auth = auth;
        this.view = view;
    }

    public void successDecider(boolean a, Contract.View v, User user) {
        if(a) {
            view.handleGetUser(user);
        }
        else {
            view.handleFailure();
        }
    }

    @Override
    public void handleFinishLogin(boolean isLoginSuccessful, String errorMessage)  {
        if (isLoginSuccessful) {
            auth.handleInitializeUser(view,this);
        } else {
            view.handleFinishLoading(errorMessage);
        }
    }

    @Override
    public void handleLogin() {
        String email = view.getEmail();
        String password = view.getPassword();

        view.handleStartLoading();
        auth.login(
                email,
                password,
                view,
                this
        );
    }

    public void recieveData(Contract.View view, String email, String pass) {
        auth.login(email, pass, view, this);
    }

}
