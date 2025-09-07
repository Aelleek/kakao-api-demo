package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll() // 정적 자원 접근 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/index.html", true) // 로그인 성공 시 index.html로 이동
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 기본: POST /logout
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 루트로 이동
                        .invalidateHttpSession(true) // 세션 무효화
                );

        return http.build();
    }
}