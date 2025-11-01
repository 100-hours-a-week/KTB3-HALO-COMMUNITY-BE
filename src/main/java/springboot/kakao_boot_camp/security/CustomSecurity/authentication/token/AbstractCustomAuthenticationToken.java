package springboot.kakao_boot_camp.security.CustomSecurity.authentication.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;

import java.util.Collection;

@AllArgsConstructor
public abstract class AbstractCustomAuthenticationToken implements CustomAuthentication {
	private Collection<GrantedAuthority> authorities;
}
