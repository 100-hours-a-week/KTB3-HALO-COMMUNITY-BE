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
public class CustomAuthenticationToken implements CustomAuthentication {    // 가독성 향상을 위한 토큰 사용
    private Object principal;
    private Object credentials;
    private String role;


    // 아직 인증되지 않은 토큰
    public CustomAuthenticationToken(Object principal, Object credentials) {
        this.principal=principal;
        this.credentials=credentials;
    }


    // 인증된 토큰
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

    @Override
    public Object getPrincipal(){
        return this.principal;
    }
}
