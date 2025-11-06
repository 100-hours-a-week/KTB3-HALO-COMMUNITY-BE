package springboot.kakao_boot_camp.domain.user.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import springboot.kakao_boot_camp.domain.user.UserRole;
import springboot.kakao_boot_camp.domain.user.entity.User;

public record UserCreateRes(
    Long id
) {
    public UserCreateRes from(User user){
        return new UserCreateRes(user.getId());
    }
    }
