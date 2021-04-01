package com.andrei.springbank.bankacc.query.handler;

import com.andrei.springbank.bankacc.query.dto.AccountLookupResponse;
import com.andrei.springbank.bankacc.query.query.FindAccountByHolderIdQuery;
import com.andrei.springbank.bankacc.query.query.FindAccountByIdQuery;
import com.andrei.springbank.bankacc.query.query.FindAccountWithBalanceQuery;
import com.andrei.springbank.bankacc.query.query.FindAllAccountsQuery;

public interface AccountQueryHandler {

    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAllAccount(FindAllAccountsQuery query);
    AccountLookupResponse findAccountsWithBalance(FindAccountWithBalanceQuery query);
}
