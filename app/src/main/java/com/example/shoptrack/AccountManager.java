package com.example.shoptrack;

import android.accounts.Account;

public interface AccountManager {
    public void deleteAccount(Account account);
    public void changePassword(Account account, String newPassword);


    //maybe implement this later
//    public void changeRole(Account account, String newRole);




}
