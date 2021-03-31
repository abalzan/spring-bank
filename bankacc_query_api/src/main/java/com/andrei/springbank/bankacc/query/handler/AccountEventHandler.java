package com.andrei.springbank.bankacc.query.handler;

import com.andrei.springbank.bankacc.core.event.AccountClosedEvent;
import com.andrei.springbank.bankacc.core.event.AccountOpenedEvent;
import com.andrei.springbank.bankacc.core.event.FundsDepositedEvent;
import com.andrei.springbank.bankacc.core.event.FundsWithdrawnEvent;

public interface AccountEventHandler {

    void on(AccountOpenedEvent event);

    void on(FundsDepositedEvent event);

    void on(FundsWithdrawnEvent event);

    void on(AccountClosedEvent event);
}
