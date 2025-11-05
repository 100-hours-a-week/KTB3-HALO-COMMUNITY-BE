package springboot.kakao_boot_camp.domain.user.dto.create;

import springboot.kakao_boot_camp.domain.user.UserRole;
import springboot.kakao_boot_camp.domain.user.entity.User;

public record UserCreateReq(
        Long id,
        String email,
        String passWord,
        String nickName,
        String profileImage,
        UserRole role
) {
    public UserCreateReq from(User user){
        return new UserCreateRes(
                user.getId(),
                user.getEmail(),
                user.getPassWord(),
                user.getNickName(),
                user.getProfileImage(),
                user.getRole()
        );
    }
}
