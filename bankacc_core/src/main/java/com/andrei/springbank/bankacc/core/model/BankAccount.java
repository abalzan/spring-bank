package com.andrei.springbank.bankacc.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    private Long id;

    private String accountHolderId;

    private Date createDate;

    private AccountType accountType;

}
