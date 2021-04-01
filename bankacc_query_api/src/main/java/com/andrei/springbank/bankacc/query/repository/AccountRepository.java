package com.andrei.springbank.bankacc.query.repository;

import com.andrei.springbank.bankacc.core.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountHolderId(String accountHolderId);

    List<BankAccount> findByBalanceGreaterThan(BigDecimal balance);

    List<BankAccount> findByBalanceLessThan(BigDecimal balance);
}
