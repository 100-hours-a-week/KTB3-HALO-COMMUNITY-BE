package springboot.kakao_boot_camp.domain.auth.Manager.signup;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.domain.user.UserRole;

import java.util.Set;

@Component
public class SignUpManager   {

    private String amdinEmail="admin@admin.com";

    public boolean isAdmin(Object object){
        if(object.equals(amdinEmail)){
            return true;
        }
        return false;
    }


}
