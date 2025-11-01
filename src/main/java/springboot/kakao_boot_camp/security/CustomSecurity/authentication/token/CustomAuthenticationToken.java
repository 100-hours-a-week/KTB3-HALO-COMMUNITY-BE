package springboot.kakao_boot_camp.security.CustomSecurity.authentication.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class CustomAuthenticationToken extends AbstractCustomAuthenticationToken {
    private Object principal;
    private Object credentials;

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<GrantedAuthority> authorities) {
        super(authorities);
    }
}
