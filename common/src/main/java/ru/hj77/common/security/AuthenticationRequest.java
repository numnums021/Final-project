package ru.hj77.common.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class AuthenticationRequest {
    private String username;
    private String password;
}
