package springboot.kakao_boot_camp.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springboot.kakao_boot_camp.security.filter.JwtFilter;
import springboot.kakao_boot_camp.security.handler.CustomAuthenticationEntryPoint;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Profile("custom-security")
public class SecurityConfig {
    private final JwtFilter springSecuritySessionFilter;      // 스프링 시큐리티 O, 세션 기반 인증 필터
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Value("${cors.allowed-origins:}")
    private List<String> allowedOrigins;

    @Value("${cors.allowed-origin-patterns:}")
    private List<String> allowedOriginPatterns;


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
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**").permitAll() // 조회는 모두 허용
                        .requestMatchers("/api/v1/posts/**").authenticated()
                        .anyRequest().permitAll()
                )                                         // 그 외 요청은 인증 필요
                .addFilterBefore(springSecuritySessionFilter, UsernamePasswordAuthenticationFilter.class) // 스프링 시큐리티 O, 세션 기반 인증 필터
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        // 환경변수에서 허용된 오리진 설정
        if (allowedOrigins != null && !allowedOrigins.isEmpty()) {
            allowedOrigins.stream()
                    .filter(origin -> origin != null && !origin.isBlank())
                    .forEach(config::addAllowedOrigin);
        }

        // 환경변수에서 허용된 오리진 패턴 설정
        if (allowedOriginPatterns != null && !allowedOriginPatterns.isEmpty()) {
            allowedOriginPatterns.stream()
                    .filter(pattern -> pattern != null && !pattern.isBlank())
                    .forEach(config::addAllowedOriginPattern);
        }

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}


