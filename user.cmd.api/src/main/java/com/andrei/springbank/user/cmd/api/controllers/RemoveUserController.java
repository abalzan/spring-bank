package com.andrei.springbank.user.cmd.api.controllers;

import com.andrei.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.andrei.springbank.user.core.dtos.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/removeUser")
@RequiredArgsConstructor
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable String id) {

        try {
            //using sendAndWait, it will only respond when the command handler has complete the work
//            commandGateway.sendAndWait(command);

            //using send it will immediately assume that the request was successful
            commandGateway.send(new RemoveUserCommand(id));

            return new ResponseEntity<>(new BaseResponse("User successfully deleted"), HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = String.format("Error while processing delete user request id - %s", id);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
