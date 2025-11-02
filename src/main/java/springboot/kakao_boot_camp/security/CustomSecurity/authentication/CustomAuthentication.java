package springboot.kakao_boot_camp.security.CustomSecurity.authentication;


import java.io.Serializable;

public interface CustomAuthentication extends Serializable {
    Object getPrincipal();
    Long getUserId();
}
