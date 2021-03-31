package com.andrei.springbank.bankacc.core.event;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FundsWithdrawnEvent {

    private String id;
    private BigDecimal amount;
    private BigDecimal balance;
}
