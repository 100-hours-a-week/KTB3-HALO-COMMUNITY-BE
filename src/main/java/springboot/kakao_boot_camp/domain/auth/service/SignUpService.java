package springboot.kakao_boot_camp.domain.auth.service;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.dto.AuthDtos.*;
import springboot.kakao_boot_camp.domain.auth.exception.DuplicateEmailException;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
//import springboot.kakao_boot_camp.global.util.JwtUtil;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SignUpService {      //Dto로 컨트롤러에서 받음

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public SignRes signUp(SignReq req) throws RuntimeException {


        // 1. 중복 확인
        if (userRepo.existsByEmail(req.email())) {
            throw new DuplicateEmailException();
        }


        User user = User.builder()
                .email(req.email())
                .passWord(passwordEncoder.encode(req.passWord()))
                .nickName(req.nickName())
                .profileImage(req.profileImage())
                .posts(null)
                .cratedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        // 3. DB에 저장
        User savedUSer = userRepo.save(user);

        // 4. Sign Response DTO 반환
        return new SignRes(savedUSer.getId());

    }



}

