package com.userfront.yuhuan.service.UserServiceImpl;



import com.userfront.yuhuan.dao.PrimaryAccountDao;
import com.userfront.yuhuan.dao.SavingAccountDao;
import com.userfront.yuhuan.domain.*;
import com.userfront.yuhuan.service.AccountService;
import com.userfront.yuhuan.service.TransactionService;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 11223145;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingAccountDao savingAccountDao;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

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

    public void deposit(String accountType, double amount, Principal principal){
        User user = userService.findByUsername(principal.getName());

        if(accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);

        } else if(accountType.equalsIgnoreCase("Saving")) {
            SavingAccount savingAccount = user.getSavingAccount();
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
            SavingTransaction savingTransaction = new SavingTransaction(date, "Deposit to Saving Account", "Account", "Finished", amount, savingAccount.getAccountBalance(),savingAccount);
            transactionService.saveSavingDepositTransaction(savingTransaction);
        }
    }


    public void withdraw(String accountType, double amount, Principal principal){
        User user = userService.findByUsername(principal.getName());

        if(accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryWithdrawTransaction(primaryTransaction);

        } else if(accountType.equalsIgnoreCase("Saving")) {
            SavingAccount savingAccount = user.getSavingAccount();
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
            SavingTransaction savingTransaction = new SavingTransaction(date, "Withdraw from Saving Account", "Account", "Finished", amount, savingAccount.getAccountBalance(),savingAccount);
            transactionService.saveSavingWithdrawTransaction(savingTransaction);
        }
    }


    private int accountGen(){
        return ++nextAccountNumber;
    }



}

