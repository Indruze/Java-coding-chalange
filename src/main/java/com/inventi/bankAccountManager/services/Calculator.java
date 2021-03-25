package com.inventi.bankAccountManager.services;

import com.inventi.bankAccountManager.account.Account;
import com.inventi.bankAccountManager.balance.Balance;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
    public static Balance calculateBalance(List<Account> accounts) {
        Balance balance = new Balance();
        balance.setBalance(accounts.stream().map(Account::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        return balance;
    }
}
