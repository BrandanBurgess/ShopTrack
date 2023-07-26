package com.example.shoptrack.managers;

import android.accounts.Account;

public interface AccountManager {
    public void deleteAccount(Account account);
    public void changePassword(Account account, String newPassword);


    //maybe implement this later
//    public void changeRole(Account account, String newRole);




}
