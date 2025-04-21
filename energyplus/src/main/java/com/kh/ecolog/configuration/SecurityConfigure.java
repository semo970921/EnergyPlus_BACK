package com.kh.ecolog.configuration;
import org.springframework.http.HttpMethod; // 꼭 추가!
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            // ✅ 마켓 관련 전체 허용
            .requestMatchers(HttpMethod.POST, "/markets/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/markets/**").permitAll()
            .requestMatchers("/markets/**").permitAll()

            // ✅ 회원 기능 허용
            .requestMatchers("/members/**").permitAll()

            // ✅ 공지사항 허용
            .requestMatchers("/notices/**").permitAll()

            // ✅ 외부 API 허용
            .requestMatchers("/apis/**").permitAll()

            // ✅ 정적 자원 허용 (썸네일 포함!)
            .requestMatchers("/uploads/**", "/resources/**", "/css/**", "/js/**", "/images/**").permitAll()

            // ✅ 나머지는 인증 필요
            .anyRequest().authenticated()
        )
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable);

    return http.build();
    }
}
