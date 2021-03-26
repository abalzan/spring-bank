package com.andrei.springbank.user.query.api.controllers;

import com.andrei.springbank.user.query.api.dtos.UserLookupResponseDto;
import com.andrei.springbank.user.query.api.queries.FindAllUsersQuery;
import com.andrei.springbank.user.query.api.queries.FindUserByIdQuery;
import com.andrei.springbank.user.query.api.queries.SearchUsersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponseDto> getAllUsers() {
        try {

            final FindAllUsersQuery query = new FindAllUsersQuery();

            final UserLookupResponseDto responseDto = checkValidQuery(query);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get all users request";
            System.out.println(e.toString());
            return new ResponseEntity<>(new UserLookupResponseDto(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/byid/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponseDto> getUserById(@PathVariable String id) {
        try {

            final FindUserByIdQuery query = new FindUserByIdQuery(id);

            final UserLookupResponseDto responseDto = checkValidQuery(query);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete get user by id request";
            System.out.println(e.toString());
            return new ResponseEntity<>(new UserLookupResponseDto(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byfilter/{filter}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponseDto> searchUserByFilter(@PathVariable String filter) {
        try {

            final SearchUsersQuery query = new SearchUsersQuery(filter);

            final UserLookupResponseDto responseDto = checkValidQuery(query);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Failed to complete user search request";
            System.out.println(e.toString());
            return new ResponseEntity<>(new UserLookupResponseDto(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private <T> UserLookupResponseDto checkValidQuery(T query) {
        final UserLookupResponseDto responseDto = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponseDto.class)).join();

        if(responseDto == null || responseDto.getUsers() == null) {
            new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return responseDto;
    }
}
