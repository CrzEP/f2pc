package com.dlg.fpc.service.exceptioon;

import org.springframework.http.HttpStatus;

/**
 * 业务异常
 */
public class BusinessException extends BizException {

    public BusinessException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public static BusinessException ex(int errorCode) {
        return new BusinessException(errorCode, "error");
    }

    public static BusinessException ex(String String) {
        return new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), String);
    }

}
