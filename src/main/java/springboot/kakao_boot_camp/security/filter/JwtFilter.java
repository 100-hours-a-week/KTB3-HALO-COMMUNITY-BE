package springboot.kakao_boot_camp.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.security.CustomUserDetails;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private String USER_ID_KEY = "userId";
    private String EMAIL_KEY = "email";
    private String ROLE_KEY = "role";

    private static final String BEARER = "Bearer";

    @Value("${jwt.access.secret}")
    String accessSecret;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. 이미 인증된 상태면 패스
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 2. 토큰 확인
        String token = resolveAccessToken(request);         // 토큰있는지 문자열 체크
        if (token == null) { // 토큰 없으면 그냥 통과
            filterChain.doFilter(request, response);        // 넘어가요
            return;
        }

        // 3. 토큰 까서 claims 획득
        Claims claims = jwtUtil.extractAccessToken(token);    // 토큰 까기 (토큰 진위여부 검증 해당 메서드에서 진행)
        Long userId = claims.get("userId", Number.class).longValue();
        String email = claims.get("email").toString();
        var authorities = Arrays.stream(
                claims.get("role").toString().split(",")
        ).map(SimpleGrantedAuthority::new).toList();


        // 4. 인증 정보 저장 = SecurityContextHolder에 등록
        CustomUserDetails customUserDetails = CustomUserDetails.from(userId, email, authorities);
        var auth = new UsernamePasswordAuthenticationToken(
                customUserDetails, null, customUserDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));     // 추가 정보 저장 (어디서 접속했는지)
        SecurityContextHolder.getContext().setAuthentication(auth);


        // 5. 다음 필터로 진행
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



