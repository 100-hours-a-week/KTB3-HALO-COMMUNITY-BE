package springboot.kakao_boot_camp.security.CustomSecurity;

import java.io.Serializable;

public class CustomSecurityContext implements Serializable {

    private static final long serialVersionUID = 1L;

    Object Authentication;

    public void setAuthentication(Object authentication){
        this.Authentication=authentication;
    }


}
