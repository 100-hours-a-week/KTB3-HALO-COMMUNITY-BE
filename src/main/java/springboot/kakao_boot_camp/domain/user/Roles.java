package springboot.kakao_boot_camp.domain.user;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_USER("admin"),
    ROLE_ADMIN("user");

    private final String description;

    Roles(String description) {
        this.description = description;
    }



    ;
}
