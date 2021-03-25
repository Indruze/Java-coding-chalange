package com.inventi.bankAccountManager.account;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@Entity
public class Account {
    private @Id @GeneratedValue Long id;
    private String accountNumber;
    private Date date;
    private String beneficiary;
    private String comment;
    private BigDecimal amount;
    private String currency;

    public Account() {
    }

    public Account(String accountNumber,
                   Date date,
                   String beneficiary,
                   String comment,
                   BigDecimal amount,
                   String currency) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.beneficiary = beneficiary;
        this.comment = comment;
        this.amount = amount;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Date getDate() {
        return date;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public String getComment() {
        return comment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Account(Map<String, Object> fieldsMap) {
        this.accountNumber = (String) fieldsMap.get("accountNumber");
        this.date = (Date) fieldsMap.get("date");
        this.beneficiary = (String) fieldsMap.get("beneficiary");
        this.comment = (String) fieldsMap.getOrDefault("comment", "");
        this.amount = (BigDecimal) fieldsMap.get("amount");
        this.currency = (String) fieldsMap.get("currency");
    }
}
