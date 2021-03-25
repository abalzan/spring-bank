package com.andrei.springbank.user.cmd.api.dtos;

import com.andrei.springbank.user.core.dtos.BaseResponse;

public class RegisterUserResponseDto extends BaseResponse {
    private String id;

    public RegisterUserResponseDto(String id, String message) {
        super(message);
        this.id = id;
    }
}
