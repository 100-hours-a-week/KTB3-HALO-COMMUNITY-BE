package springboot.kakao_boot_camp.security.config;


import lombok.RequiredArgsConstructor;
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
import springboot.kakao_boot_camp.security.CustomSecurity.filter.CustomSessionFilter;

@Configuration
@Profile("custom-security")
@RequiredArgsConstructor
public class WebConfig {
    private final CustomSessionFilter customSessionFilter;                      // 스프링 시큐리티 X, 세션 기반 인증 필터


    @Bean
    public CustomPasswordEncoder customPasswordEncoder(){
        return new CustomPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<CustomSessionFilter> sessionFilter() {
        FilterRegistrationBean<CustomSessionFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(customSessionFilter);
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
