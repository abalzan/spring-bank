package com.andrei.springbank.bankacc.cmd.controller;

import com.andrei.springbank.bankacc.cmd.command.CloseAccountCommand;
import com.andrei.springbank.bankacc.cmd.command.WithdrawFundsCommand;
import com.andrei.springbank.bankacc.core.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/closeBankAccount")
public class CloseAccountController {

    private final CommandGateway commandGateway;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable String id) {

        try {
            final CloseAccountCommand command = CloseAccountCommand.builder()
                    .id(id)
                    .build();

            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("Bank account successfully closed"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to close bank account for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
