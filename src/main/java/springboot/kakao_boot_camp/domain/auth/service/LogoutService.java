package springboot.kakao_boot_camp.domain.auth.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.global.manager.session.SessionAuthManager;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final SessionAuthManager sessionManager;

    public void sessionLogout(HttpServletRequest request, HttpServletResponse response) {
        // 세션과 관련 쿠키 삭제
        sessionManager.delete(request, response);
    }
}
