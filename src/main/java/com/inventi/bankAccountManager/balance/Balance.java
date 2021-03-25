package com.inventi.bankAccountManager.balance;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;

    public void BalanceDto() {
    }

    public void BalanceDto(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceDto{" +
                "balance=" + balance +
                '}';
    }
}
