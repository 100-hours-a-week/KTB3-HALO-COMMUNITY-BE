package springboot.kakao_boot_camp.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt.JwtLoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt.JwtLoginRes;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginRes;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;




    // 세션 + 쿠키 : 세션 발급 방식
    public SessionLoginRes sessionLogin(SessionLoginReq req, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws RuntimeException {

        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new InvalidLoginException());

        final HttpSession session = servletRequest.getSession();
        session.setAttribute("userId", user.getId().toString());
        session.setMaxInactiveInterval(3600);       // 세션 유효시간 설정, 단위 시간 초 ( 3600 : 1시간)


        return SessionLoginRes.from(user);
    }

}
