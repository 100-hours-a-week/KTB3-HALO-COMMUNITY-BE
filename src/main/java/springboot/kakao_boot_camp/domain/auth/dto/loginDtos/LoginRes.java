package springboot.kakao_boot_camp.domain.auth.dto.loginDtos;


import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;

public record LoginRes(
        Long userId,
        String accessToken,
        String refreshToken
) {
    public static LoginRes from(CustomAuthUserWithoutSpringScurity user, String accessToken, String refreshToken) {
        return new LoginRes(
                user.getUserId(),
                accessToken,
                refreshToken
        );
    }

    public static LoginRes fromWithoutRefreshToken(Long userId, String accessToken) {
        return new LoginRes(
                userId,
                accessToken,
                null
        );
    }
}