package springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt;

import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;

public record JwtLoginRes(
        Long userId,
        String accessToken,
        String refreshToken
) {
    public static JwtLoginRes from(CustomAuthUserWithoutSpringScurity user, String accessToken, String refreshToken) {
        return new JwtLoginRes(
                user.getUserId(),
                accessToken,
                refreshToken
        );

    }
}
