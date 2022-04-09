package ru.hj77.server.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import ru.hj77.server.service.SecurityService;

import javax.sql.DataSource;


@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String INFO_ALL_END_POINT = "/info/clients/";
    private final String INFO_ADMIN_END_POINT = "/info/clients/**";

    private SecurityService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(INFO_ALL_END_POINT).authenticated()
                .antMatchers(INFO_ADMIN_END_POINT).hasRole("ADMIN")
                .and()
                .httpBasic();
    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$QUeVmi4ICx3r7hsA6S4F1uSwFKD0NaMhT8g7/m5p/XgH2PJxOlZHy")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$QUeVmi4ICx3r7hsA6S4F1uSwFKD0NaMhT8g7/m5p/XgH2PJxOlZHy")
//                .roles("ADMIN" ,"USER")
//                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (jdbcUserDetailsManager.userExists(user.getUsername())){
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(admin.getUsername())){
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
        return jdbcUserDetailsManager;
    }

//    @Bean
//    public DaoAuthenticationProvider users(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(service);
//        return authenticationProvider;
//    }

}
