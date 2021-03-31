package com.andrei.springbank.bankacc.cmd.controller;

import com.andrei.springbank.bankacc.cmd.command.OpenAccountCommand;
import com.andrei.springbank.bankacc.cmd.dto.OpenAccountResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/openBankAccount")
public class OpenAccountController {

    private final CommandGateway commandGateway;

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<OpenAccountResponse> openAccount(@Valid @RequestBody OpenAccountCommand command) {
        final String id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("successfully opened a new bank account", id), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to open a new bank account for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
