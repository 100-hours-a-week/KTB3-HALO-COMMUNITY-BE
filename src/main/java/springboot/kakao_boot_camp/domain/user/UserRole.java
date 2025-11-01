package springboot.kakao_boot_camp.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }



    ;
}
