package springboot.kakao_boot_camp.domain.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.dto.AuthDtos;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public AuthDtos.LoginRes login(AuthDtos.LoginReq req, HttpServletResponse servletRes) throws RuntimeException {
        String accessTokenSample = "asfdafdfadsasdfadfsa";

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(req.email(), req.passWord());

//        try{

            Authentication auth = authenticationManagerBuilder.getObject().authenticate(authToken);
            System.out.println("s");
//            String accessToken = JwtUtil.createAccessToken(auth);
//        }


        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new InvalidLoginException());


        if (!passwordEncoder.matches(req.passWord(), user.getPassWord())) {
            throw new InvalidLoginException();
        }


        return AuthDtos.LoginRes.from(user, accessTokenSample);

    }

}
