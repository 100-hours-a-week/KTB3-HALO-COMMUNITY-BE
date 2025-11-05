package springboot.kakao_boot_camp.domain.user.dto.delete.soft;

import springboot.kakao_boot_camp.domain.user.entity.User;

import java.time.LocalDateTime;

public record UserSoftDeleteRes(
        Long userId,
        Boolean deleted,
        LocalDateTime deletedAt
) {
    public UserSoftDeleteRes from(User user) {
        return new UserSoftDeleteRes(
                user.getId(),
                user.isDeleted(),
                user.getDeletedAt()
        );
    }
}
