package com.example.employeemanagement.config;

import com.example.employeemanagement.filter.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final AuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/employee-management/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE)
                        .hasAuthority("admin")
                        .anyRequest().authenticated()
                ).authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
