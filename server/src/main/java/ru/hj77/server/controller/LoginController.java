package ru.hj77.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hj77.common.communication.security.AuthenticationRequest;
import ru.hj77.common.communication.security.AuthenticationResponse;
import ru.hj77.server.security.JwtUtil;
import ru.hj77.server.security.MyUsersDetailsService;

@AllArgsConstructor
@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private MyUsersDetailsService usersDetailsService;
    private JwtUtil jwtTokenUtil;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Неверный логин или пароль", e);
        }

        final UserDetails userDetails = usersDetailsService
                .loadUserByUsername(request.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
