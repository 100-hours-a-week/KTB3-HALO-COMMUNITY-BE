package springboot.kakao_boot_camp.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository postRepo;

}
