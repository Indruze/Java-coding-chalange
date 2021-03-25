package com.inventi.bankAccountManager.repository;

import com.inventi.bankAccountManager.account.Account;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

    List<Account> findByDateAfterAndDateBefore(Date fromDate, Date toDate);
    List<Account> findByDateAfter(Date fromDate);
    List<Account> findByDateBefore(Date toDate);
}
