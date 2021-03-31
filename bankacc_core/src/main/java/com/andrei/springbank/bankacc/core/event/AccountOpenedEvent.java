package com.andrei.springbank.bankacc.core.event;

import com.andrei.springbank.bankacc.core.model.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent {

    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private Date creationDate;
    private BigDecimal openingBalance;
}
