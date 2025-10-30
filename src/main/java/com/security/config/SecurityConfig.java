package com.security.config;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity  //웹 보안기능 활성화
@Configuration  //스프링 Bean 인식(클래스로 등록)
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "auth", "/home/**", "/css/**").permitAll() //해당 경로 접근 허용
                        .requestMatchers("/user/**").hasRole("USER") // USER만 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 가능
                        .anyRequest().authenticated() //나머지는 인증 필요
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .permitAll() //누구나 접근 가능
                        )
                .logout(logout -> logout
                                .logoutRequestMatcher
                                        (new AntPathRequestMatcher("/logout", "GET"))
                                .logoutSuccessUrl("/auth") // 로그아웃 후 이동할 페이지 설정
                                .invalidateHttpSession(true) // 세션삭제
                                .deleteCookies("JSESSIONID") // 쿠키 삭제
                        );

        return http.build();
    } // SecurityFilterChain 닫기

    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(); // 인스턴스 객채 생성
    }

    // 사용자 계정 생성
    @Bean
    UserDetailsService userDetailsService(){

        UserDetails user = User.builder()
                .username("guest")
                .password(passwordEncoder().encode("1234")) // 비밀번호 암호화
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("1234")) // 비밀번호 암호화
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}