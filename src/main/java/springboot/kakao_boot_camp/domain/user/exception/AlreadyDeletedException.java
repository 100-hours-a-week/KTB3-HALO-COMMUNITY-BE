package springboot.kakao_boot_camp.domain.user.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class AlreadyDeletedException extends BusinessException {
    public AlreadyDeletedException(){
        super(ErrorCode.ALREADY_DELETE_USR);
    }
}
