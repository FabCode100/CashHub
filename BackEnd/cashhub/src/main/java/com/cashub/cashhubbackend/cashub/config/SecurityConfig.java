package com.cashub.cashhubbackend.cashub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adminpassword"))
                        .roles("USER", "ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(admin);
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // Configure authorization (URL-based, method-based, etc.)
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }
}