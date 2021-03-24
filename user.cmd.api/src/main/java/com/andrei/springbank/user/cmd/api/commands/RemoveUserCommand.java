package com.andrei.springbank.user.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveUserCommand {

    //Identifier used by axon to know  which instance of an aggregate type should handle the command message
    //The field caring the aggregate identifier in the command object must be annotated with @TargetAggregateIdentifier
    @TargetAggregateIdentifier
    private String id;
}
