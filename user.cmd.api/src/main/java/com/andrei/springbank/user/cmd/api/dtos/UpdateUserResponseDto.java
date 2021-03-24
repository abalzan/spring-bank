package com.andrei.springbank.user.cmd.api.dtos;

public class UpdateUserResponseDto extends BaseResponse {
    private String id;

    public UpdateUserResponseDto(String id, String message) {
        super(message);
        this.id = id;
    }
}
