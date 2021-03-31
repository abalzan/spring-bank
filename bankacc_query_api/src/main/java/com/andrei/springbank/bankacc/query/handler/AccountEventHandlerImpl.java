package com.andrei.springbank.bankacc.query.handler;

import com.andrei.springbank.bankacc.core.event.AccountClosedEvent;
import com.andrei.springbank.bankacc.core.event.AccountOpenedEvent;
import com.andrei.springbank.bankacc.core.event.FundsDepositedEvent;
import com.andrei.springbank.bankacc.core.event.FundsWithdrawnEvent;
import com.andrei.springbank.bankacc.core.model.BankAccount;
import com.andrei.springbank.bankacc.query.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler {

    private final AccountRepository accountRepository;

    @EventHandler
    @Override
    public void on(AccountOpenedEvent event) {
        final BankAccount bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .createDate(event.getCreationDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @EventHandler
    @Override
    public void on(FundsDepositedEvent event) {
        accountRepository.findById(event.getId()).ifPresent(bankAccount -> {
            bankAccount.setBalance(event.getBalance());
            accountRepository.save(bankAccount);
        });

    }

    @EventHandler
    @Override
    public void on(FundsWithdrawnEvent event) {
        accountRepository.findById(event.getId()).ifPresent(bankAccount -> {
            bankAccount.setBalance(event.getBalance());
            accountRepository.save(bankAccount);
        });
    }

    @EventHandler
    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
