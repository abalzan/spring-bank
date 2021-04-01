package com.andrei.springbank.bankacc.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery {

    private String id;
}
