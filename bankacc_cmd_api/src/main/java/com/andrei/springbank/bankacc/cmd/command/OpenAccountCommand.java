package com.andrei.springbank.bankacc.cmd.command;

import com.andrei.springbank.bankacc.core.model.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class OpenAccountCommand {

    //Identifier used by axon to know  which instance of an aggregate type should handle the command message
    //The field caring the aggregate identifier in the command object must be annotated with @TargetAggregateIdentifier
    @TargetAggregateIdentifier
    private String id;


    @NotNull(message = "No account holder id was supplied")
    private String accountHolderId;

    @NotNull(message = "No account type was supplied")
    private AccountType accountType;

    @Min(value = 0, message = "opening balance must be at least 50.")
    private BigDecimal openingBalance;

}
