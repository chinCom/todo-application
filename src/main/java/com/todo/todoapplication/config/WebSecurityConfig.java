package com.todo.todoapplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * The SecurityFilterChain bean defines which URL paths should be secured and
         * which should not. Specifically, the / and /home paths are configured to not
         * require any authentication. All other paths must be authenticated.
         */
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/bootstrap/css/bootstrap.min.css",
                        "/bootstrap/css/Login-Form-Basic-icons.css",
                        "/bootstrap/js/bootstrap.min.js",
                        "/img/encryption.png")
                .permitAll()
                .anyRequest().authenticated()

        ).formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());

        return http.build();
    }

    // set user name and pasword
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("chinCom@123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
