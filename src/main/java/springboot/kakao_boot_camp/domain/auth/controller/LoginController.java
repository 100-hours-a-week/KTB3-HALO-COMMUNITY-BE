package springboot.kakao_boot_camp.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


    @PostMapping()
    public ResponseEntity<ApiResponse<LoginRes>> login(@RequestBody @Valid LoginReq req, HttpServletRequest servletRequest) {
        LoginRes res = loginService.login(req, servletRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.LOGIN_SUCCESS, res));
    }

}
