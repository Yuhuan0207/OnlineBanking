package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.PrimaryAccount;
import com.userfront.yuhuan.domain.PrimaryTransaction;
import com.userfront.yuhuan.domain.SavingAccount;
import com.userfront.yuhuan.domain.SavingTransaction;

import java.security.Principal;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingAccount createSavingAccount();

    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);

}