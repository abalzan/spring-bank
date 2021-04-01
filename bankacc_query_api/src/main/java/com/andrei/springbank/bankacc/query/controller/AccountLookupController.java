package com.andrei.springbank.bankacc.query.controller;

import com.andrei.springbank.bankacc.query.dto.AccountLookupResponse;
import com.andrei.springbank.bankacc.query.dto.EqualityType;
import com.andrei.springbank.bankacc.query.query.FindAccountByHolderIdQuery;
import com.andrei.springbank.bankacc.query.query.FindAccountByIdQuery;
import com.andrei.springbank.bankacc.query.query.FindAccountWithBalanceQuery;
import com.andrei.springbank.bankacc.query.query.FindAllAccountsQuery;
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

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bankAccountLookup")
public class AccountLookupController {

    private final QueryGateway queryGateway;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try{
            final FindAllAccountsQuery query = new FindAllAccountsQuery();

            final AccountLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            String safeErrorMessage = "Failed to complete get all accounts request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable String id) {
        try{
            final FindAccountByIdQuery query = new FindAccountByIdQuery(id);

            final AccountLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            String safeErrorMessage = "Failed to complete account by id request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byHolderId/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable String accountHolderId) {
        try{
            final FindAccountByHolderIdQuery query = new FindAccountByHolderIdQuery(accountHolderId);

            final AccountLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            String safeErrorMessage = "Failed to complete get account by holder id request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByEqualityType(@PathVariable EqualityType equalityType,
                                                                          @PathVariable BigDecimal balance) {
        try{
            final FindAccountWithBalanceQuery query = new FindAccountWithBalanceQuery(equalityType, balance);

            final AccountLookupResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            String safeErrorMessage = "Failed to complete get account with balance request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
