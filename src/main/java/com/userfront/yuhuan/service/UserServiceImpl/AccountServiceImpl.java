package com.userfront.yuhuan.service.UserServiceImpl;



import com.userfront.yuhuan.dao.PrimaryAccountDao;
import com.userfront.yuhuan.dao.SavingAccountDao;
import com.userfront.yuhuan.domain.PrimaryAccount;
import com.userfront.yuhuan.domain.SavingAccount;
import com.userfront.yuhuan.service.AccountService;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 11223145;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingAccountDao savingAccountDao;

    @Autowired
    private UserService userService;

    public PrimaryAccount createPrimaryAccount(){
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountDao.save(primaryAccount); //only when the account is persisted in DAO can the accountGen() work

        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    public SavingAccount createSavingAccount(){
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(accountGen());

        savingAccountDao.save(savingAccount);

        return savingAccountDao.findByAccountNumber(savingAccount.getAccountNumber());
    }

    private int accountGen(){
        return ++nextAccountNumber;
    }
}