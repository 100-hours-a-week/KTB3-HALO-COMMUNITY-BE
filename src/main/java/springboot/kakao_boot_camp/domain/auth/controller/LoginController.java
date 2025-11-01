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
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session.SessionLoginRes;
import springboot.kakao_boot_camp.domain.auth.service.LoginService;
import springboot.kakao_boot_camp.global.api.ApiResponse;
import springboot.kakao_boot_camp.global.api.SuccessCode;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/session")
    public ResponseEntity<ApiResponse<SessionLoginRes>> sessionLogin(@RequestBody @Valid SessionLoginReq req,  HttpServletRequest servletRequest) {
        SessionLoginRes res = loginService.sessionLogin(req, servletRequest);    //data 얻기x

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.LOGIN_SUCCESS, res));
    }
}
