package com.steve.springbootblogapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class WebSecurityConfiguration {

    private final String[] WHITELIST = {
            "/register",
            "/login",
            "/h2-console/**",
            "/"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers(HttpMethod.GET,"/posts/*").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                 .usernameParameter("email")
                 .passwordParameter("password")
                 .defaultSuccessUrl("/", true)
                 .failureUrl("/login?error").permitAll()
                 .and().logout()
                 .logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                  .and().httpBasic();

        // Can remove when no longer using H2 Console
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();

    }

}
