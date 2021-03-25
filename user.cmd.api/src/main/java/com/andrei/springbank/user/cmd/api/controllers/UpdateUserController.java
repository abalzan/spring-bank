package com.andrei.springbank.user.cmd.api.controllers;

import com.andrei.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.andrei.springbank.user.core.dtos.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/updateUser")
@RequiredArgsConstructor
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserCommand command) {

        try {
            command.setId(id);
            //using sendAndWait, it will only respond when the command handler has complete the work
//            commandGateway.sendAndWait(command);

            //using send it will immediately assume that the request was successful
            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("User successfully updated"), HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = String.format("Error while processing update user request id - %s", id);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
