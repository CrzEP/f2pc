package com.dlg.fpc.service.exceptioon.bussiness;

import com.dlg.fpc.service.comm.ErrorCode;
import com.dlg.fpc.service.exceptioon.BusinessException;

public class EnCodeException extends BusinessException {

    public EnCodeException(String errorMsg) {
        super(ErrorCode.ENCODE_EXCEPTION, errorMsg);

    }
}
