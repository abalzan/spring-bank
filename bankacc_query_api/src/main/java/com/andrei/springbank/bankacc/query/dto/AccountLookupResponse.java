package com.andrei.springbank.bankacc.query.dto;

import com.andrei.springbank.bankacc.core.dto.BaseResponse;
import com.andrei.springbank.bankacc.core.model.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }
    public AccountLookupResponse(String message, List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public AccountLookupResponse(String message, BankAccount account) {
        super(message);
        this.accounts = new ArrayList<>();
        this.accounts.add(account);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }
}
