package springboot.kakao_boot_camp.security.CustomSecurity.authentication;


import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

public interface CustomAuthentication extends Serializable {
    Long getUserId();
}
