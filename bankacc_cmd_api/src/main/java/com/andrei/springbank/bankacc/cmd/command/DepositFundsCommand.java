package com.andrei.springbank.bankacc.cmd.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Builder
public class DepositFundsCommand {
    //Identifier used by axon to know  which instance of an aggregate type should handle the command message
    //The field caring the aggregate identifier in the command object must be annotated with @TargetAggregateIdentifier
    @TargetAggregateIdentifier
    private String id;

    @Min(value = 1, message = "the deposit amount must be greater than 0")
    private BigDecimal amount;
}
