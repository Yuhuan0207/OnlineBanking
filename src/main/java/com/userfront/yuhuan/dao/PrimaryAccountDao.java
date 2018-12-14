package com.userfront.yuhuan.dao;

import com.userfront.yuhuan.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long>{
    PrimaryAccount findByAccountNumber(int accountNumber);
}
