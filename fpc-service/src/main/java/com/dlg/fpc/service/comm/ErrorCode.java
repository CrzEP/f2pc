package com.dlg.fpc.service.comm;

/**
 * 异常代码
 */
public interface ErrorCode {

    int DEF_ERROR = 500;

    String DEF_ERROR_MSG = "error";

    /**
     * 参数绑定异常
     */
    int ARGUMENT_BIND_ERROR = 10000;

    String ARGUMENT_BIND_MSG = "Argument bind error";

    /**
     * 加密异常
     */
    int ENCODE_EXCEPTION = 20000;

    String ENCODE_EXCEPTION_MSG = "Encode exception error";
}
