package springboot.kakao_boot_camp.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.kakao_boot_camp.domain.user.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}


