package springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal;

import lombok.Getter;

@Getter
public class CustomAuthUserWithoutSpringScurity {
    Long userId;
    String email;
    String role;


    public CustomAuthUserWithoutSpringScurity(Long userId, String email, String role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
    }



    public static CustomAuthUserWithoutSpringScurity from(Long userId, String email, String role) {
        return new CustomAuthUserWithoutSpringScurity(userId, email, role);
    }
}
