package springboot.kakao_boot_camp.domain.auth.controller;

import com.mysql.cj.log.Log;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.kakao_boot_camp.domain.auth.Manager.login.jwt.RefreshTokenCookieManager;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.LoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.LoginRes;
import springboot.kakao_boot_camp.domain.auth.service.LoginService;
import springboot.kakao_boot_camp.global.api.ApiResponse;
import springboot.kakao_boot_camp.global.api.SuccessCode;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final RefreshTokenCookieManager refreshTokenCookieManager;


    @PostMapping
    public ResponseEntity<ApiResponse<LoginRes>> login(
            @RequestBody @Valid LoginReq req,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {

        // 1. 액세스 토큰 포함 DTO 생성
        LoginRes res = loginService.login(req, servletRequest);

        // 2. 리프레시 토큰 포함
        refreshTokenCookieManager.addRefreshTokenCookie(servletResponse, res.refreshToken());

        // 3. Login response Dto에서 Refresh token 빼기
        LoginRes result = LoginRes.fromWithoutRefreshToken(res.userId(), res.accessToken());
        return ResponseEntity
                .ok(ApiResponse.success(SuccessCode.LOGIN_SUCCESS, result));
    }


}
