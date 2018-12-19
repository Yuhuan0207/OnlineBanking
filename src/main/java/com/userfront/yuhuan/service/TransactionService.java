package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.*;

import java.security.Principal;
import java.util.List;

public interface TransactionService {
    public List<PrimaryTransaction> findPrimaryTransactionList(String username);

    public List<SavingTransaction> findSavingTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingDepositTransaction(SavingTransaction savingTransaction);

    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingWithdrawTransaction(SavingTransaction savingTransaction);

    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) throws Exception;

    List<Recipient> findRecipientList(Principal principal);
    Recipient saveRecipient(Recipient recipient);
    Recipient findRecipientByName(String recipientName);
    void deleteRecipientByName(String recipientName);
}
