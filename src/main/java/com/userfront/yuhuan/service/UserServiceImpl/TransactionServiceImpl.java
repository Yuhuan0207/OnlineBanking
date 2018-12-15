package com.userfront.yuhuan.service.UserServiceImpl;

import com.userfront.yuhuan.dao.PrimaryTransactionDao;
import com.userfront.yuhuan.dao.SavingTransactionDao;
import com.userfront.yuhuan.domain.PrimaryTransaction;
import com.userfront.yuhuan.domain.SavingTransaction;
import com.userfront.yuhuan.domain.User;
import com.userfront.yuhuan.service.TransactionService;
import com.userfront.yuhuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingTransactionDao savingTransactionDao;

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


}
