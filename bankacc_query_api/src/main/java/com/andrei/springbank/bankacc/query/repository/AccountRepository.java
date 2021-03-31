package com.andrei.springbank.bankacc.query.repository;

import com.andrei.springbank.bankacc.core.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, String> {
}
