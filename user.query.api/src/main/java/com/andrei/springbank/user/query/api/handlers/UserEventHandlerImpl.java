package com.andrei.springbank.user.query.api.handlers;

import com.andrei.springbank.user.core.events.UserRegisteredEvent;
import com.andrei.springbank.user.core.events.UserRemovedEvent;
import com.andrei.springbank.user.core.events.UserUpdatedEvent;
import com.andrei.springbank.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@ProcessingGroup("user-group") //Similar to a consumer group when a event handler consumes an event,
// axon will track the offset to make sure that within a given processing group that you will always consume the latest event
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;


    @EventHandler // will be performed when an event is received or consumed from the event bus
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());

    }

    @EventHandler // will be performed when an event is received or consumed from the event bus
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler // will be performed when an event is received or consumed from the event bus
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());

    }
}
