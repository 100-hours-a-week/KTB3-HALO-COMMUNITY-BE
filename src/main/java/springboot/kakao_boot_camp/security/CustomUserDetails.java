package springboot.kakao_boot_camp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public  class CustomUserDetails extends User {
    private Long id;

    public CustomUserDetails(
            springboot.kakao_boot_camp.domain.user.entity.User user,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(user.getEmail(), user.getPassWord(), authorities);
    }

    public CustomUserDetails(
            Long userId,
            String email,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(email, "", authorities);
        this.id = userId;
    }

    static public CustomUserDetails from(Long userId, String email, Collection<? extends GrantedAuthority> authorities) {
        return new CustomUserDetails(userId, email, authorities);
    }
}
