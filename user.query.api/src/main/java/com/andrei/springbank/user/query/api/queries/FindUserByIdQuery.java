package com.andrei.springbank.user.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

//this class contain id field it represent a query where we want to return user by id
//still figuring out why we can use the repository to this

@Data
@AllArgsConstructor
public class FindUserByIdQuery {
 private String id;
}
