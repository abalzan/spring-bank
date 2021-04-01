package com.andrei.springbank.bankacc.query.query;

import com.andrei.springbank.bankacc.query.dto.EqualityType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery {

    private EqualityType equalityType;
    private BigDecimal balance;
}
