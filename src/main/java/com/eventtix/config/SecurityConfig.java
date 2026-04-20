package com.eventtix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Keep disabled for easy JS API calls
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC: Anyone can see these
                        .requestMatchers("/", "/events", "/events/", "/css/**", "/js/**").permitAll()

                        // AUTHENTICATED: Must be logged in (User OR Admin) to book
                        .requestMatchers("/api/tickets/*/book").authenticated()

                        // ADMIN ONLY: Must have ROLE_ADMIN to create events or release tickets
                        .requestMatchers("/events/new", "/api/tickets/*/cancel").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/events", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/events"));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin123").roles("ADMIN").build();

        UserDetails customer = User.withDefaultPasswordEncoder()
                .username("customer").password("pass123").roles("USER").build();

        return new InMemoryUserDetailsManager(admin, customer);
    }
}