package springboot.kakao_boot_camp.security.CustomSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboot.kakao_boot_camp.global.constant.session.SessionConst;
import springboot.kakao_boot_camp.security.CustomAuthUser;
import springboot.kakao_boot_camp.security.CustomSecurity.Context.CustomSecurityContextHolder;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.token.CustomAuthenticationToken;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomSessionFilter extends OncePerRequestFilter {



    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 0. 로그인 및 회원가입 제외
        String uri = request.getRequestURI();
        if(uri.startsWith("/api/v1/auth/")){
            filterChain.doFilter(request,response);
            return;
        }

        // 1. 이미 인증된 경우 통과
        if (CustomSecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 2. 세션 확인
        HttpSession session = request.getSession(false);    // 자동으로 redis에서 세션 ID 조회
        if (session == null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 3. 세션에서 값 꺼내기 (Null 체크)
        Object userIdObj = session.getAttribute(SessionConst.USER_ID_KEY);
        Object emailObj = session.getAttribute(SessionConst.EMAIL_KEY);
        Object roleObj = session.getAttribute(SessionConst.ROLE_KEY);
        if (userIdObj == null || emailObj == null || roleObj == null) {
            filterChain.doFilter(request, response);
            return;
        }


        // 4. 인증 객체 생성 및 할당
        Long userId = ((Number) userIdObj).longValue();  // 레디스에 json으로 저장해서 Long -> Integer변환으로 인한 형변환
        String email = (String) emailObj;
        String role = roleObj.toString();
        CustomAuthUserWithoutSpringScurity customAuthUser = CustomAuthUserWithoutSpringScurity.from(userId, email, role);
        CustomAuthenticationToken token = new CustomAuthenticationToken(
                customAuthUser, "", customAuthUser.getRole());
        CustomSecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }


}
