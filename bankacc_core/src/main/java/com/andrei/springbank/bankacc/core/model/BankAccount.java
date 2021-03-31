package com.andrei.springbank.bankacc.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankAccount {

    @Id
    private String id;

    private String accountHolderId;

    private Date createDate;

    private AccountType accountType;

    private BigDecimal balance;

}
