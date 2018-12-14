package com.userfront.yuhuan.dao;

import com.userfront.yuhuan.domain.SavingAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingAccountDao extends CrudRepository<SavingAccount, Long>{
    SavingAccount findByAccountNumber(int accountNumber);
}
