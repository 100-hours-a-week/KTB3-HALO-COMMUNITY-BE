package springboot.kakao_boot_camp.security.CustomSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.security.CustomSecurity.Context.CustomSecurityContextHolder;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.token.CustomAuthenticationToken;

import java.util.Arrays;

@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    private static final String BEARER = "Bearer";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        // 0. 로그인 및 회원가입 제외
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/v1/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }


        // 1. 이미 인증된 경우 통과
        if (CustomSecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 2. 엑세스 토큰 개봉
        String accessToken = resolveAccessToken(request);           // 토큰있는지 문자열 체크
        if (accessToken == null) {                                  // 토큰 없으면 그냥 통과
            filterChain.doFilter(request, response);                // 넘어가요
            return;
        }

        // 3. 엑세스 토큰 Claim 획득
        Claims claims = jwtUtil.extractAccessToken(accessToken);    // 토큰 까기 (토큰 진위여부 검증 해당 메서드에서 진행)
        Long userId = claims.get("userId", Number.class).longValue();
        String email = claims.get("email").toString();
        String role = claims.get("role").toString();


        // 4. SecurityContext에 등록
        CustomAuthUserWithoutSpringScurity customAuthUser = CustomAuthUserWithoutSpringScurity.from(userId, email, role);
        CustomAuthenticationToken token = new CustomAuthenticationToken(
                customAuthUser, "", customAuthUser.getRole());
        CustomSecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }


    private String resolveAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER)) {
            return header.substring(BEARER.length()).trim();
        }
        return null;
    }

}




