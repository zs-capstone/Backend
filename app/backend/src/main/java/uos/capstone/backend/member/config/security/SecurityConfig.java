package uos.capstone.backend.member.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uos.capstone.backend.member.config.jwt.JwtAccessDeniedHandler;
import uos.capstone.backend.member.config.jwt.JwtAuthenticationFilter;
import uos.capstone.backend.member.config.jwt.JwtEntryPoint;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // h2 database 테스트가 원활하도록 관련 API 들은 전부 무시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers( "/favicon.ico","/swagger-ui/**","/api-docs/**");
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http.cors().and()
                .csrf().disable()

                // exception handling 할 때 우리가 만든 클래스를 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // h2-console 을 위한 설정을 추가
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .logout().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .and()
                .authorizeRequests()
                .antMatchers("/api/member/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().hasRole("USER")   // 나머지 API 는 전부 인증 필요

                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
