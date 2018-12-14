package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.PrimaryAccount;
import com.userfront.yuhuan.domain.SavingAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingAccount createSavingAccount();
}