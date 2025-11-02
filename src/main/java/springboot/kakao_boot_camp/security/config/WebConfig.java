package springboot.kakao_boot_camp.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springboot.kakao_boot_camp.domain.auth.util.CustomPasswordEncoder;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.security.CustomSecurity.filter.CustomJwtFilter;
import springboot.kakao_boot_camp.security.CustomSecurity.filter.CustomSessionFilter;

@Configuration
@Profile("custom-security")
@RequiredArgsConstructor
public class WebConfig {
    private final JwtUtil jwtUtil;

    @Value("${auth.type}")  // ← yml에 auth.type 있어야 함
    private String authType;


    @Bean
    public FilterRegistrationBean<?> authFilter() {
        if ("jwt".equalsIgnoreCase(authType)) {
            return createJwtFilter();
        } else if ("session".equalsIgnoreCase(authType)) {
            return createSessionFilter();
        }
        throw new IllegalArgumentException("존재하지 않는 필터입니다. (yml-auth-type 확인 필요)");  // ← 둘 다 아닐 때만 실행
    }

    public FilterRegistrationBean<CustomSessionFilter> createSessionFilter() {
        CustomSessionFilter customSessionFilter = new CustomSessionFilter();
        FilterRegistrationBean<CustomSessionFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(customSessionFilter);
        bean.addUrlPatterns("/api/*");
        bean.setOrder(1);
        return bean;
    }


    public FilterRegistrationBean<CustomJwtFilter> createJwtFilter() {
        CustomJwtFilter customJwtFilter = new CustomJwtFilter(jwtUtil);
        FilterRegistrationBean<CustomJwtFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(customJwtFilter);
        bean.addUrlPatterns("/api/*");
        bean.setOrder(1);
        return bean;
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:3000");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
}
