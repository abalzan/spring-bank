package com.andrei.springbank.bankacc.cmd.controller;

import com.andrei.springbank.bankacc.cmd.command.DepositFundsCommand;
import com.andrei.springbank.bankacc.cmd.command.OpenAccountCommand;
import com.andrei.springbank.bankacc.cmd.dto.OpenAccountResponse;
import com.andrei.springbank.bankacc.core.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/depositFunds")
public class DepositFundsController {

    private final CommandGateway commandGateway;

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable String id, @Valid @RequestBody DepositFundsCommand command) {

        try {
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("funds successfully deposited"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to deposit funds into bank account for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
