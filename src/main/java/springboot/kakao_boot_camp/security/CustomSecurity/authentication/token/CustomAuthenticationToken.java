package springboot.kakao_boot_camp.security.CustomSecurity.authentication.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;

import java.util.Collection;

@Getter
@Setter
public class CustomAuthenticationToken implements CustomAuthentication {
    private Object principal;
    private Object credentials;
    private String role;

    public CustomAuthenticationToken(Object principal, Object credentials, String role) {
        this.principal=principal;
        this.credentials=credentials;
        this.role=role;
    }

    @Override
    public Long getUserId() {
        if (principal instanceof CustomAuthUserWithoutSpringScurity) {
            return ((CustomAuthUserWithoutSpringScurity) principal).getUserId();
        }
        return null;
    }
}
