package com.andrei.springbank.bankacc.query.handler;

import com.andrei.springbank.bankacc.core.model.BankAccount;
import com.andrei.springbank.bankacc.query.dto.AccountLookupResponse;
import com.andrei.springbank.bankacc.query.dto.EqualityType;
import com.andrei.springbank.bankacc.query.query.FindAccountByHolderIdQuery;
import com.andrei.springbank.bankacc.query.query.FindAccountByIdQuery;
import com.andrei.springbank.bankacc.query.query.FindAccountWithBalanceQuery;
import com.andrei.springbank.bankacc.query.query.FindAllAccountsQuery;
import com.andrei.springbank.bankacc.query.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        return accountRepository.findById(query.getId())
                .map(bankAccount -> new AccountLookupResponse("Bank Account Successfully returned", bankAccount))
                .orElseGet(() -> new AccountLookupResponse("No Bank accout found for id - " + query.getId()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        return accountRepository.findByAccountHolderId(query.getAccountHolderId())
                .map(bankAccount -> new AccountLookupResponse("Bank Account Successfully returned", bankAccount))
                .orElseGet(() -> new AccountLookupResponse("No Bank account found for id - " + query.getAccountHolderId()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccount(FindAllAccountsQuery query) {
        final List<BankAccount> bankAccountList = accountRepository.findAll();
        if (bankAccountList.isEmpty()) {
            new AccountLookupResponse("No Bank account found");
        }

        return new AccountLookupResponse("Successfully returned " + bankAccountList.size() + " Bank accounts ", bankAccountList);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsWithBalance(FindAccountWithBalanceQuery query) {
        final List<BankAccount> bankAccountList = query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());

        return  bankAccountList.isEmpty()
                ? new AccountLookupResponse("No Bank account found")
                : new AccountLookupResponse("Successfully returned " + bankAccountList.size() + " Bank account(s) ", bankAccountList);

    }
}
