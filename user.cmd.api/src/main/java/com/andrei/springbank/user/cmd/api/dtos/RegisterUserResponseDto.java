package com.andrei.springbank.user.cmd.api.dtos;

public class RegisterUserResponseDto extends BaseResponse {
    private String id;

    public RegisterUserResponseDto(String id, String message) {
        super(message);
        this.id = id;
    }
}
