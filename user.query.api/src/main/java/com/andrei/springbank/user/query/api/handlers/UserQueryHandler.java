package com.andrei.springbank.user.query.api.handlers;

import com.andrei.springbank.user.query.api.dtos.UserLookupResponseDto;
import com.andrei.springbank.user.query.api.queries.FindAllUsersQuery;
import com.andrei.springbank.user.query.api.queries.FindUserByIdQuery;
import com.andrei.springbank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookupResponseDto getUserById(FindUserByIdQuery query);
    UserLookupResponseDto searchUsers(SearchUsersQuery query);
    UserLookupResponseDto getAllUsers(FindAllUsersQuery query);

}
