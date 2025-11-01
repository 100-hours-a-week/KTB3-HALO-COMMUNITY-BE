package springboot.kakao_boot_camp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Getter
@Setter
public class CustomAuthUser extends User {
    Long userId;

    public CustomAuthUser(Long userId, String email, String role) {
        super(email, "", makeAuthorityList(role));
        this.userId=userId;
    }

    private static Set<SimpleGrantedAuthority> makeAuthorityList(String role) {
        return Set.of(new SimpleGrantedAuthority(role));
    }

    public static CustomAuthUser from(Long userId, String email, String role) {
        return new CustomAuthUser(userId, email, role);
    }

}
