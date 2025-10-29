package springboot.kakao_boot_camp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUser extends User {
    private springboot.kakao_boot_camp.domain.user.entity.User user;
    public CustomUser(springboot.kakao_boot_camp.domain.user.entity.User user){
        super(user.getEmail(), user.getPassWord(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
}
