package com.andrei.springbank.bankacc.cmd.agregate;

import com.andrei.springbank.bankacc.cmd.command.CloseAccountCommand;
import com.andrei.springbank.bankacc.cmd.command.DepositFundsCommand;
import com.andrei.springbank.bankacc.cmd.command.OpenAccountCommand;
import com.andrei.springbank.bankacc.cmd.command.WithdrawFundsCommand;
import com.andrei.springbank.bankacc.core.event.AccountClosedEvent;
import com.andrei.springbank.bankacc.core.event.AccountOpenedEvent;
import com.andrei.springbank.bankacc.core.event.FundsDepositedEvent;
import com.andrei.springbank.bankacc.core.event.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {

    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private BigDecimal balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {
        final AccountOpenedEvent event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolderId(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .creationDate(new Date())
                .openingBalance(command.getOpeningBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.getId();
        this.accountHolderId = event.getAccountHolderId();
        this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command) {
        final BigDecimal amount = command.getAmount();
        final FundsDepositedEvent event;
        event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance.add(amount))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event) {
        this.balance = this.balance.add(event.getAmount());
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command) {
        final BigDecimal amount = command.getAmount();
        if (this.balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Withdraw declined, insufficient funds");
        }

        final FundsDepositedEvent event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance.subtract(amount))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event) {
        this.balance = this.balance.subtract(event.getAmount());
    }

    @CommandHandler
    public void handle(CloseAccountCommand command) {
        final AccountClosedEvent event = AccountClosedEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event) {
        AggregateLifecycle.markDeleted();
    }


}
