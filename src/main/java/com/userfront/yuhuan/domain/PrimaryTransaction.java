package com.userfront.yuhuan.domain;
import java.math.BigDecimal;
import java.util.*;

public class PrimaryTransaction {
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;                 // doesn't use for calculation, so leave as 'double'
    private BigDecimal availableBalance;   // BigDecimal to resolve calculation problem
    private PrimaryAccount primaryAccount;

    public PrimaryTransaction(){ //default constructor

    }

    public PrimaryTransaction(Date date, String description, String type, String status,
                              double amount, BigDecimal availableBalance, PrimaryAccount primaryAccount) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.primaryAccount = primaryAccount;
    }
}
