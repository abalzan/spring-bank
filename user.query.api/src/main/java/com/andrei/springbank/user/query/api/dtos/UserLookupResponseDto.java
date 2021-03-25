package com.andrei.springbank.user.query.api.dtos;

import com.andrei.springbank.user.core.dtos.BaseResponse;
import com.andrei.springbank.user.core.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class UserLookupResponseDto extends BaseResponse {
    private List<User> users;

    public UserLookupResponseDto(User user) {
        super(null);
        this.users = Collections.singletonList(user);
    }

    public UserLookupResponseDto(String message, User user) {
        super(message);
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public UserLookupResponseDto(String message) {
        super(message);
    }

    public UserLookupResponseDto(List<User> users) {
        super(null);
        this.users = users;
    }
}
