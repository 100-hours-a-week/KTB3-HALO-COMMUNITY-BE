package springboot.kakao_boot_camp.domain.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.dto.AuthDtos.*;
import springboot.kakao_boot_camp.domain.auth.exception.DuplicateEmailException;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.constant.DefaultImage;
import springboot.kakao_boot_camp.global.exception.DuplicateResourceException;
import springboot.kakao_boot_camp.domain.user.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {      //Dto로 컨트롤러에서 받음

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

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

    public LoginRes login(LoginReq req) throws RuntimeException {
        String accessTokenSample = "asfdafdfadsasdfadfsa";

//        UsernamePasswordAuthenticationToken authToken

        User user = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new InvalidLoginException());


        if (!passwordEncoder.matches(req.passWord(), user.getPassWord())) {
            throw new InvalidLoginException();
        }


        return LoginRes.from(user, accessTokenSample);

    }


}

