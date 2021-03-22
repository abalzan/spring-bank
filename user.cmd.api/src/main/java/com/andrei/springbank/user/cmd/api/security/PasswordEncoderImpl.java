package com.andrei.springbank.user.cmd.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String hashPassword(String password) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(password);
    }
}
