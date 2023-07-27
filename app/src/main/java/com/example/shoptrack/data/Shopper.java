package com.example.shoptrack.data;

import android.accounts.Account;

import com.example.shoptrack.managers.AccountManager;
import com.example.shoptrack.managers.OrderManager;

public class Shopper extends User implements AccountManager, OrderManager {

//    public final String id; dont need rn

    public Shopper(String email, String role) {
        super(email, role);
    }

    @Override
    public void deleteAccount(Account account) {
        // TODO remove account from db
    }

    @Override
    public void changePassword(Account account, String newPassword) {
        // TODO change password stored in db

    }
//    maybe implement this later
//    @Override
//    public void changeRole(Account account, String newRole) {
//        // TODO change role stored in db
//
//
//    }

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void removeOrder(Order order) {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void getOrder(Order order) {

    }

    @Override
    public void getAllOrders() {

    }


}
