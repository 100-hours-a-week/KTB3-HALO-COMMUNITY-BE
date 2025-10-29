package springboot.kakao_boot_camp.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.user.Roles;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.exception.UserNotFoundException;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();


        User user = userRepo.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if(user.getRole().equals(Roles.ROLE_USER)){
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        }

        CustomUserDetails customUserDetails = CustomUserDetails.from(user.getId(), user.getEmail(), user.getPassWord(), authorities);

        return customUserDetails;
    }
}

class CustomUserDetails extends org.springframework.security.core.userdetails.User{
    private Long id;

    public CustomUserDetails(String email, String passWord, List<GrantedAuthority> authorities){
        super(email,passWord,authorities);
    }

    static public CustomUserDetails from(Long id, String email, String passWord, List<GrantedAuthority> authorities){
        return new CustomUserDetails(email, passWord, authorities);
    }

}
