package com.bizflow.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bizflow.authorization.JwtValidator;



@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(authorizer -> authorizer
                .requestMatchers("/api/super-admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .addFilterBefore(jwtValidator(), BasicAuthenticationFilter.class)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    JwtValidator jwtValidator() {
        return new JwtValidator();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Arrays.asList(
                    "http://localhost:5173",
                    "http://localhost:3000"
            ));
            
            cors.setAllowedMethods(Collections.singletonList("*"));
            cors.setAllowedHeaders(Collections.singletonList("*"));
            cors.setExposedHeaders(Arrays.asList("Authorization"));
            cors.setAllowCredentials(true);
            cors.setMaxAge(3600L);
            return cors;
        };
    }
}
