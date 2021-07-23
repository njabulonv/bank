package com.wonderlabz.bank.dao;

import com.wonderlabz.bank.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, String> {
}
