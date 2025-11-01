package springboot.kakao_boot_camp.security.CustomSecurity.Context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;

import java.io.Serializable;

@Getter
@Setter
public class CustomSecurityContext implements Serializable {    // 하나의 요청에 하나의 컨텍스트 인스턴스가 스레드 로컬에 들어감.

    private static final long serialVersionUID = 1L;

    CustomAuthentication authentication;




}
