package com.userfront.yuhuan.service.UserServiceImpl;

import com.userfront.yuhuan.dao.*;
import com.userfront.yuhuan.domain.*;
import com.userfront.yuhuan.service.TransactionService;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private RecipientDao recipientDao;

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

    public List<Recipient> findRecipientList(Principal principal){
        String username = principal.getName();
        List<Recipient> recipientList = recipientDao.findAll().stream()                         //convert list to stream
                .filter(recipient -> username.equals(recipient.getUser().getUsername()))        //filter the line, equals to username
                .collect(Collectors.toList());

        return recipientList;
    }

    public Recipient saveRecipient(Recipient recipient){
        return recipientDao.save(recipient);
    }

    public Recipient findRecipientByName(String recipientName){
        return recipientDao.findByName(recipientName);
    }

    public void deleteRecipientByName(String recipientName){
        recipientDao.deleteByName(recipientName);
    }

    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount){
        if(accountType.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        }else if(accountType.equalsIgnoreCase("Saving")){
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();

            SavingTransaction savingTransaction = new SavingTransaction(date, "Transfer to recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), savingAccount.getAccountBalance(), savingAccount);
            savingTransactionDao.save(savingTransaction);
        }
    }

}