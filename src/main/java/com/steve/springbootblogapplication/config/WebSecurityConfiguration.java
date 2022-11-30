package com.steve.springbootblogapplication.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class WebSecurityConfiguration {

    private final String[] ENDPOINT_WHITELIST = {
            "/register",
            "/login",
            "/"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) ->
                 authorize.requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers(ENDPOINT_WHITELIST).permitAll()
                    .requestMatchers(HttpMethod.GET, "/posts/*").permitAll()
                    .anyRequest().authenticated());
        return http.build();

    }

}
