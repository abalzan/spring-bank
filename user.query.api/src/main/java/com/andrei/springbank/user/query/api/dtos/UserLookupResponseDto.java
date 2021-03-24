package com.andrei.springbank.user.query.api.dtos;

import com.andrei.springbank.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class UserLookupResponseDto {
    private List<User> users;

    public UserLookupResponseDto(User user) {
        this.users = Collections.singletonList(user);
    }
}
