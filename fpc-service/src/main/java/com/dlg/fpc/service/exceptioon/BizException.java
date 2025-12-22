package com.dlg.fpc.service.exceptioon;

import com.dlg.fpc.service.comm.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 最大异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException{

    private int errorCode;
    private String errorMsg;

    public BizException(int errorCode, String errorMsg) {}
    public BizException(String errorMsg) {
        this.errorCode = ErrorCode.DEF_ERROR;
        this.errorMsg = errorMsg;
    }

}
