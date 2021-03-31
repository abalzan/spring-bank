package com.andrei.springbank.bankacc.cmd.controller;

import com.andrei.springbank.bankacc.cmd.command.DepositFundsCommand;
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
@RequestMapping("/api/v1/withdrawFunds")
public class WithdrawFundsController {

    private final CommandGateway commandGateway;

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable String id, @Valid @RequestBody WithdrawFundsCommand command) {

        try {
            command.setId(id);
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new BaseResponse("funds successfully withdraw"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to withdraw funds into bank account for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
