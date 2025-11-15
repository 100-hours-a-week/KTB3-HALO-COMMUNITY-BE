package springboot.kakao_boot_camp.domain.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import springboot.kakao_boot_camp.domain.user.enums.UserRole;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long Id;

    @Column(nullable = false)
    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false)
    @Size(min = 2, max = 10, message = "닉네임은 2~10자여야 합니다.")
    private String nickName;

    @Column(nullable = true)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserRole role;

    @Column(name = "deleted")
    @Builder.Default
    private boolean deleted = false;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    LocalDateTime deletedAt;


}
