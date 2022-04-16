package ru.hj77.common.security;

import lombok.Value;

@Value
public class AuthenticationRequest {
    String username;
    String password;
}
