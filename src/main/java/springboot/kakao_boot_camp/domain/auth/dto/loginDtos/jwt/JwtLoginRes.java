package springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt;

import springboot.kakao_boot_camp.domain.user.entity.User;

public record JwtLoginRes(
        Long userId,
        String email,
        String nickName,
        String accessToken,
        String refreshToken
) {
    public static JwtLoginRes from(User user, String accessToken, String refreshToken) {
        return new JwtLoginRes(
                user.getId(),
                user.getEmail(),
                user.getNickName(),
                accessToken,
                refreshToken
        );

    }
}
