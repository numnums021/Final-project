package ru.hj77.server.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.hj77.server.service.SecurityService;


@AllArgsConstructor
@Configuration
@EnableWebSecurity//(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String INFO_ALL_END_POINT = "/info/clients/**";
    private final String INFO_ADMIN_END_POINT = "/info/**";
    private final String USER_END_POINT = "/card/**";

    private SecurityService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(INFO_ADMIN_END_POINT).hasRole("ADMIN")
//                .antMatchers(USER_END_POINT).hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider users(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(service);
        return authenticationProvider;
    }

}
