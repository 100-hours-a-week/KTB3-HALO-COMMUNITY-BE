package springboot.kakao_boot_camp.domain.auth.exception;


import springboot.kakao_boot_camp.global.api.ErrorCode;

public class InvalidTokenTypeException extends springboot.kakao_boot_camp.global.exception.BusinessException {
    public InvalidTokenTypeException(){
        super(ErrorCode.SESSION_EXPIRED);
    }
}
