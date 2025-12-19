package com.dlg.fpc.service.exceptioon;

/**
 * 业务异常
 */
public class BusinessException extends BizException {

    private static final int defaultErrorCode = 500;

    public BusinessException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public static BusinessException ex(int errorCode) {
        return new BusinessException(errorCode, "error");
    }

    public static BusinessException ex(String String) {
        return new BusinessException(defaultErrorCode, String);
    }

}
