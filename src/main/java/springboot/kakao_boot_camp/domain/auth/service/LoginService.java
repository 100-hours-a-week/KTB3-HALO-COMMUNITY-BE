package springboot.kakao_boot_camp.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.Manager.CustomAuthenticationManager;
import springboot.kakao_boot_camp.domain.auth.Manager.login.jwt.JwtAuthManager;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt.JwtLoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt.JwtLoginRes;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginRes;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.auth.util.CustomPasswordEncoder;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import springboot.kakao_boot_camp.domain.auth.Manager.login.session.SessionAuthManager;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.token.CustomAuthenticationToken;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    //    private final PasswordEncoder passwordEncoder;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final SessionAuthManager sessionManager;
    private final CustomAuthenticationManager customAuthenticationManager;
    private final JwtAuthManager jwtAuthManager;


    // 1. 세션 + 쿠키 기반 로그인
    public SessionLoginRes sessionLogin(SessionLoginReq req, HttpServletRequest servletReq) throws RuntimeException {

        // 1. 사용자 존재 검색 및 반환
        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new InvalidLoginException());


        // 2. 입력한 비밀번호가 유효한지 검사
        if (!customPasswordEncoder.match(req.passWord(), user.getPassWord())) {
            throw new InvalidLoginException();
        }

        // 3. 세션 생성 및 클라이언트에게 쿠키로 세션 전달
        sessionManager.create(servletReq, user);


        // 4. 응답 반환
        return SessionLoginRes.from(user);
    }

    // 2. jwt 기반 로그인
    public JwtLoginRes jwtLogin(JwtLoginReq req, HttpServletRequest servletReq) throws RuntimeException {

        // 3. jwt 토큰 반환
        CustomAuthenticationToken token = new CustomAuthenticationToken(req.email(), req.passWord());
        CustomAuthentication auth = customAuthenticationManager.authenticate(token);

        JwtAuthManager.TokenPair tokenPair =  jwtAuthManager.createTokens(auth);

        return JwtLoginRes.from((CustomAuthUserWithoutSpringScurity)auth.getPrincipal(), tokenPair.accessToken(), tokenPair.refreshToken());
    }

}

