package com.andrei.springbank.user.cmd.api.aggregates;

import com.andrei.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.andrei.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.andrei.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.andrei.springbank.user.cmd.api.security.PasswordEncoder;
import com.andrei.springbank.user.cmd.api.security.PasswordEncoderImpl;
import com.andrei.springbank.user.core.events.UserRegisteredEvent;
import com.andrei.springbank.user.core.events.UserRemovedEvent;
import com.andrei.springbank.user.core.events.UserUpdatedEvent;
import com.andrei.springbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {

    //The aggregate Identifier marks the reference point to the user aggregate object that axon uses to know which aggregate a given command is targeting
    //We mock the Id's of our command objects with @TargetAggregateIdentifier
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    //Axon requires an empty constructor
    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    // Here a  constructor that creates the aggregate for the RegisterUserCommand
    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        passwordEncoder = new PasswordEncoderImpl();

        final User newUser = command.getUser();
        newUser.setId(command.getId());
        final String password = newUser.getAccount().getPassword();
        final String hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        final UserRegisteredEvent event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();

        //Will store the event on the event store and publish it on the event bus
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        final User updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        final String password = updatedUser.getAccount().getPassword();
        final String hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        final UserUpdatedEvent event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        //Will store the event on the event store and publish it on the event bus
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        final UserRemovedEvent event = new UserRemovedEvent();
        event.setId(event.getId());

        //Will store the event on the event store and publish it on the event bus
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();

    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
