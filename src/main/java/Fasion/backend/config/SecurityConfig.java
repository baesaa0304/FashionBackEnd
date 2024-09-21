package Fasion.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 기능 비활성화
        // Ajax POST/PUT/DELETE 요청에서 CSRF 토큰을 서버로 전송하지 않으면 403 에러가 발생.
        // -> CSRF 기능 비활성화.
        http.csrf((csrf) -> csrf.disable());

        // 로그인 페이지 설정 - 스프링에서 제공하는 기본 로그인 페이지를 사용.
        http.formLogin(Customizer.withDefaults());

        // 로그아웃 이후 이동할 페이지
        http.logout((logout) -> logout.logoutSuccessUrl("/"));
        return http.build();
    }
}
