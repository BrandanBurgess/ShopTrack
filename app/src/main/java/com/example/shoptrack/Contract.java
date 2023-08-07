package com.example.shoptrack;

import android.content.Context;

import com.example.shoptrack.data.Auth;
import com.example.shoptrack.data.Shopper;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.data.User;

public interface Contract {
    interface Model{
        void login(String email, String password, Contract.View view, Presenter presenter);
        void handleInitializeUser(View view, Presenter presenter);
    }

    interface View {
        void handleFinishLoading(String toastMessage);
        void handleStartLoading();
        void handleGetCustomer(Shopper shopper);
        void handleGetStore(Store store);
        void handleGetUser(User user);
        void handleFailure();
        void login();
    }

    interface Presenter{
        void handleLogin();
        void handleFinishLogin(boolean isLoginSuccessful, String errorMessage);
    }
}