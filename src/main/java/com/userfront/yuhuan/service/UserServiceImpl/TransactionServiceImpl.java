package com.userfront.yuhuan.service.UserServiceImpl;

import com.userfront.yuhuan.dao.PrimaryAccountDao;
import com.userfront.yuhuan.dao.PrimaryTransactionDao;
import com.userfront.yuhuan.dao.SavingAccountDao;
import com.userfront.yuhuan.dao.SavingTransactionDao;
import com.userfront.yuhuan.domain.*;
import com.userfront.yuhuan.service.TransactionService;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingTransactionDao savingTransactionDao;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingAccountDao savingAccountDao;

    public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    public List<SavingTransaction> findSavingTransactionList(String username){
        User user = userService.findByUsername(username);
        List<SavingTransaction> savingTransactionList = user.getSavingAccount().getSavingTransactionList();

        return savingTransactionList;
    }

    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction){
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingDepositTransaction(SavingTransaction savingTransaction){
        savingTransactionDao.save(savingTransaction);
    }

    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction){
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingWithdrawTransaction(SavingTransaction savingTransaction){
        savingTransactionDao.save(savingTransaction);
    }

    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) throws Exception {
        if(transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Saving")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from " + transferFrom + " to " + transferTo, "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if (transferFrom.equalsIgnoreCase("Saving") && transferTo.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);

            Date date = new Date();

            SavingTransaction savingTransaction = new SavingTransaction(date, "Between account transfer from " + transferFrom + " to " + transferTo, "Transfer", "Finished", Double.parseDouble(amount), savingAccount.getAccountBalance(), savingAccount);
            savingTransactionDao.save(savingTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }





}
