package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.PrimaryAccount;
import com.userfront.yuhuan.domain.PrimaryTransaction;
import com.userfront.yuhuan.domain.SavingAccount;
import com.userfront.yuhuan.domain.SavingTransaction;

import java.util.List;

public interface TransactionService {
    public List<PrimaryTransaction> findPrimaryTransactionList(String username);

    public List<SavingTransaction> findSavingTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingDepositTransaction(SavingTransaction savingTransaction);

    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingWithdrawTransaction(SavingTransaction savingTransaction);

    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) throws Exception;

}
