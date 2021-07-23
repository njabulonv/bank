package com.wonderlabz.bank.dao;

import com.wonderlabz.bank.model.BankStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatementRepository extends JpaRepository<BankStatement, Integer> {


    @Query(value = "SELECT TOP 1 * FROM BANK_STATEMENT WHERE account_number = ?1", nativeQuery = true)
    BankStatement findByAccountNumber(String accountNumber);


}
