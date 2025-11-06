package springboot.kakao_boot_camp.domain.user.dto.update;

import springboot.kakao_boot_camp.domain.user.UserRole;
import springboot.kakao_boot_camp.domain.user.entity.User;

import java.time.LocalDateTime;

public record UserUpdateReq(
        Long userId,
        String email,
        String nickName,
        String bio,
        String profileImage,
        UserRole role, 
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Boolean deleted
) {
    public UserUpdateReq from(User user) {
        return new UserUpdateReq(
                user.getId(),
                user.getEmail(),
                user.getNickName(),
                user.getBio(),
                user.getProfileImage(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt(),
                user.isDeleted()
        );
    }
}
