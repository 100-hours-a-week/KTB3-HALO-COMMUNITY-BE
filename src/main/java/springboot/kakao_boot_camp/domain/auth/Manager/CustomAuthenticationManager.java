package springboot.kakao_boot_camp.domain.auth.Manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.domain.auth.util.CustomPasswordEncoder;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.token.CustomAuthenticationToken;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    public CustomAuthentication authenticate(CustomAuthenticationToken token){
        String email = token.getPrincipal().toString();
        String passWord = token.getCredentials().toString();

        // 1. 유요한 유저 인지 검증
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidLoginException());


        // 2. 유효한 비밀번호인지 검증
        if (!customPasswordEncoder.match(passWord, user.getPassWord())) {
            throw new InvalidLoginException();
        }

        CustomAuthUserWithoutSpringScurity customUser = CustomAuthUserWithoutSpringScurity.from(user.getId(), user.getEmail(), user.getRole().name());

        return new CustomAuthenticationToken(customUser, "", customUser.getRole());
    }
}
