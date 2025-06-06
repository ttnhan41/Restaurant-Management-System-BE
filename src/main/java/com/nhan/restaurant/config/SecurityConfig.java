package com.nhan.restaurant.config;

import com.nhan.restaurant.security.JwtAuthenticationFilter;
import com.nhan.restaurant.security.JwtTokenProvider;
import com.nhan.restaurant.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);

        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests(auths -> auths
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/menu-items/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/menu-items").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/menu-items/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/menu-items/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET,"/api/tables/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/tables").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/tables/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/tables/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/orders/all").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/orders/updateStatus").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/reservations/all").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/reservations/updateStatus").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/reviews/all").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET,"/api/reviews/menu-items/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/users/all").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/users/current-user").hasAnyRole("MANAGER", "GUEST")
                .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("MANAGER")
                .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
