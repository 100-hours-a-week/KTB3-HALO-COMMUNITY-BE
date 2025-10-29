package springboot.kakao_boot_camp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import springboot.kakao_boot_camp.domain.auth.dto.signDtos.SignReq;
import springboot.kakao_boot_camp.domain.auth.dto.signDtos.SignRes;
import springboot.kakao_boot_camp.domain.auth.service.SignUpService;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;



@ExtendWith(MockitoExtension.class)
public class SignUpServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignUpService authService;

    @Test
    public void signUpTest() {
        //given
        String email = "test@test.com";
        String passWord = "test123";
        String nickName = "test1";
        String profileImage = "http://testImage.com";


        User user = User.builder()
                .Id(1L)
                .email(email)
                .passWord(passWord)
                .nickName(nickName)
                .profileImage(profileImage)
                .build();


        given(userRepo.existsByEmail(email)).willReturn(false);
        given(passwordEncoder.encode(passWord)).willReturn("encoded");
        given(userRepo.save(any(User.class))).willReturn(user);

        // when
        SignRes res = authService.signUp( new SignReq(email,passWord, nickName, profileImage));

        // then
        assertThat(res.id()).isEqualTo(1L);
    }
}
