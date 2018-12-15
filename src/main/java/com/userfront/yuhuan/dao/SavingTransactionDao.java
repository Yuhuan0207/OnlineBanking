package com.userfront.yuhuan.dao;

import com.userfront.yuhuan.domain.SavingTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingTransactionDao extends CrudRepository<SavingTransaction, Long> {
    List<SavingTransaction> findAll();
}