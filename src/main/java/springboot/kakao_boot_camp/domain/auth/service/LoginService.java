package springboot.kakao_boot_camp.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginRes;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.auth.util.CustomPasswordEncoder;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import springboot.kakao_boot_camp.global.manager.session.SessionAuthManager;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SessionAuthManager sessionManager;




    // 세션 + 쿠키 : 세션 발급 방식
    public SessionLoginRes sessionLogin(SessionLoginReq req, HttpServletRequest servletReq) throws RuntimeException {

        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new InvalidLoginException());

        if(!customPasswordEncoder.match(req.passWord(),user.getPassWord())){
            throw new InvalidLoginException();
        }

        sessionManager.create(servletReq, user);


        return SessionLoginRes.from(user);
    }

}
