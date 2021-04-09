package com.andrei.springbank.user.cmd.api.bootstrap;

import com.andrei.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.andrei.springbank.user.core.models.Account;
import com.andrei.springbank.user.core.models.Role;
import com.andrei.springbank.user.core.models.User;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class Bootstrap implements CommandLineRunner {

    private final CommandGateway commandGateway;

    @Override
    public void run(String... args) throws Exception {

        final String id = UUID.randomUUID().toString();

        final RegisterUserCommand userCommand = RegisterUserCommand.builder()
                .id(id)
                .user(User.builder()
                        .id(id)
                        .firstname("john")
                        .lastname("doe")
                        .emailAddress("johnd@springbank.com")
                        .account(Account.builder()
                                .username("johnd")
                                .password("passw01")
                                .roles(Arrays.asList(Role.READ_PRIVILEGE, Role.WRITE_PRIVILEGE))
                                .build())
                        .build())
                .build();

        commandGateway.send(userCommand);
    }
}
