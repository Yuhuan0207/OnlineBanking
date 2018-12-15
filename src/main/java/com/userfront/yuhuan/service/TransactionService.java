package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.PrimaryTransaction;
import com.userfront.yuhuan.domain.SavingTransaction;

import java.util.List;

public interface TransactionService {
    public List<PrimaryTransaction> findPrimaryTransactionList(String username);

    public List<SavingTransaction> findSavingTransactionList(String username);

    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    public void saveSavingDepositTransaction(SavingTransaction savingTransaction);
}
