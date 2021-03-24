
package com.andrei.springbank.user.query.api.handlers;

import com.andrei.springbank.user.core.models.User;
import com.andrei.springbank.user.query.api.dtos.UserLookupResponseDto;
import com.andrei.springbank.user.query.api.queries.FindAllUsersQuery;
import com.andrei.springbank.user.query.api.queries.FindUserByIdQuery;
import com.andrei.springbank.user.query.api.queries.SearchUsersQuery;
import com.andrei.springbank.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    // @QueryHandler in axon an object might declare one or more query handler methods
    //the declared parameters of the methods define which query message it will receive
    //in our case it will receive "FindUserByIdQuery, SearchUsersQuery or FindAllUsersQuery"
    @QueryHandler
    @Override
    public UserLookupResponseDto getUserById(FindUserByIdQuery query) {
        final Optional<User> user = userRepository.findById(query.getId());

        return user.map(UserLookupResponseDto::new).orElseThrow();
    }

    @QueryHandler
    @Override
    public UserLookupResponseDto searchUsers(SearchUsersQuery query) {
        final List<User> users = userRepository.findByFilterRegex(query.getFilter());
        return new UserLookupResponseDto(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponseDto getAllUsers(FindAllUsersQuery query) {
        final List<User> users = userRepository.findAll();
        return new UserLookupResponseDto(users);
    }
}
