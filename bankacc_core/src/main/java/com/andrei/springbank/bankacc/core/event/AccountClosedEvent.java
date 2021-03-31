package com.andrei.springbank.bankacc.core.event;

import com.andrei.springbank.bankacc.core.model.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class AccountClosedEvent {

    private String id;
}
