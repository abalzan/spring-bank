package com.andrei.springbank.user.query.api.handlers;

import com.andrei.springbank.user.core.events.UserRegisteredEvent;
import com.andrei.springbank.user.core.events.UserRemovedEvent;
import com.andrei.springbank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);
}
