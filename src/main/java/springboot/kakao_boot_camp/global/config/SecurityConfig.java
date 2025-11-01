package springboot.kakao_boot_camp.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springboot.kakao_boot_camp.security.CustomSecurity.filter.CustomSessionFilter;
import springboot.kakao_boot_camp.security.SpringSecurity.filter.SpringSecuritySessionFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final SpringSecuritySessionFilter springSecuritySessionFilter;      // 스프링 시큐리티 O, 세션 기반 인증 필터
    private final CustomSessionFilter customSessionFilter;                      // 스프링 시큐리티 X, 세션 기반 인증 필터


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .requestCache(cache -> cache.disable())

                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )                                         // 그 외 요청은 인증 필요
//                .addFilterBefore(springSecuritySessionFilter, UsernamePasswordAuthenticationFilter.class) // 스프링 시큐리티 O, 세션 기반 인증 필터
                .addFilterBefore(customSessionFilter, UsernamePasswordAuthenticationFilter.class)   // 스프링 시큐리티 X, 세션 기반 인증 필터
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // 정확히 지정
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
