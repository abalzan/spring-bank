package com.andrei.springbank.user.cmd.api.controllers;

import com.andrei.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.andrei.springbank.user.cmd.api.dtos.RegisterUserResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
@RequiredArgsConstructor
public class RegisterUserController {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponseDto> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        final String id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            //using sendAndWait, it will only respond when the command handler has complete the work
//            commandGateway.sendAndWait(command);

            //using send it will immediately assume that the request was successful
            commandGateway.send(command);

            return new ResponseEntity<>(new RegisterUserResponseDto(id, "User successfully registered"), HttpStatus.CREATED);
        } catch (Exception e) {
            String safeErrorMessage = String.format("Error while processing register user request id - %s", id);
            return new ResponseEntity<>(new RegisterUserResponseDto(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
