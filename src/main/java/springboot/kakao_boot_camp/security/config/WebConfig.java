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
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.security.CustomSecurity.filter.CustomJwtFilter;

@Configuration
@Profile("custom-security")
@RequiredArgsConstructor
public class WebConfig {
    private final JwtUtil jwtUtil;


    @Bean
    public FilterRegistrationBean<CustomJwtFilter> createJwtFilter() {
        CustomJwtFilter customJwtFilter = new CustomJwtFilter(jwtUtil);
        FilterRegistrationBean<CustomJwtFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(customJwtFilter);
        bean.addUrlPatterns("/api/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
