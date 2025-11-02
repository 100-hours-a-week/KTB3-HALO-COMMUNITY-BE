package springboot.kakao_boot_camp.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.Manager.CustomAuthenticationManager;
import springboot.kakao_boot_camp.domain.auth.Manager.login.jwt.JwtAuthManager;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.LoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.LoginRes;
import springboot.kakao_boot_camp.domain.auth.util.CustomPasswordEncoder;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.token.CustomAuthenticationToken;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    //    private final PasswordEncoder passwordEncoder;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final CustomAuthenticationManager customAuthenticationManager;
    private final JwtAuthManager jwtAuthManager;



    // 2. jwt 기반 로그인
    public LoginRes jwtLogin(LoginReq req, HttpServletRequest servletReq) throws RuntimeException {

        // 3. jwt 토큰 반환
        CustomAuthenticationToken token = new CustomAuthenticationToken(req.email(), req.passWord());
        CustomAuthentication auth = customAuthenticationManager.authenticate(token);

        JwtAuthManager.TokenPair tokenPair =  jwtAuthManager.createTokens(auth);

        return LoginRes.from((CustomAuthUserWithoutSpringScurity)auth.getPrincipal(), tokenPair.accessToken(), tokenPair.refreshToken());
    }

}

