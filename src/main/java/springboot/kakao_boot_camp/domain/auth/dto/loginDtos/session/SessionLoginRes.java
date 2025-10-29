package springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session;

import springboot.kakao_boot_camp.domain.user.entity.User;

public record SessionLoginRes(
        Long userId,
        String email,
        String nickName
) {
    public static SessionLoginRes from(User user) {
        return new SessionLoginRes(
                user.getId(),
                user.getEmail(),
                user.getNickName()
        );
    }
}
