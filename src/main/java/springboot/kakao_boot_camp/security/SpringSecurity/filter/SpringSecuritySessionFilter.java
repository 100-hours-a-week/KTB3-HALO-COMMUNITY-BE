package springboot.kakao_boot_camp.security.SpringSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboot.kakao_boot_camp.security.CustomAuthUser;

import java.io.IOException;

@Component
public class SpringSecuritySessionFilter extends OncePerRequestFilter {

    private String USER_ID_KEY = "userId";
    private String EMAIL_KEY = "email";
    private String ROLE_KEY = "role";



    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. 이미 인증된 경우 통과
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 2. 세션 확인
        HttpSession session = request.getSession(false);
        if (session == null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 3. 세션에서 값 꺼내기 (Null 체크)
        Object userIdObj = session.getAttribute(USER_ID_KEY);
        Object emailObj = session.getAttribute(EMAIL_KEY);
        Object roleObj = session.getAttribute(ROLE_KEY);
        if (userIdObj == null || emailObj == null || roleObj == null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 4. 인증 객체 생성 및 할당
        Long userId = ((Number) userIdObj).longValue();  // 레디스에 json으로 저장해서 Long -> Integer변환으로 인한 형변환
        String email = (String) emailObj;
        String role = roleObj.toString();
        CustomAuthUser customAuthUser = CustomAuthUser.from(userId, email, role);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                customAuthUser, "", customAuthUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }
}



