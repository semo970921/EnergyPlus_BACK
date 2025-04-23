package com.kh.ecolog.configuration;
import org.springframework.http.HttpMethod; // 꼭 추가!
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfigure {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	 http
         .cors(Customizer.withDefaults())
         .csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(auth -> 
             auth.anyRequest().permitAll()  // 모든 요청 다 허용
         )
         .formLogin(AbstractHttpConfigurer::disable)
         .httpBasic(AbstractHttpConfigurer::disable);

     return http.build();
    }
}