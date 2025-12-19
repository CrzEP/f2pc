package com.dlg.fpc.service.exceptioon;

import com.dlg.fpc.service.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.dlg.fpc.service.comm.TConst.DEFAULT_MESSAGE_LEN;

/**
 * 异常处理器
 *
 * @author lingui
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class HxExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleHxException(BusinessException ex) {
        return Result.error(ex.getErrorCode(), ex.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public Result<Object> handleHxException(BizException ex) {
        return Result.error(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public Result<Object> handleHxException(HttpMessageConversionException ex) {
        return Result.error(ex.getMessage().substring(0, DEFAULT_MESSAGE_LEN));
    }

    /**
     * 最大捕获异常
     * @param ex ex
     * @return 消息
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleHxException(Exception ex) {
        return Result.error(ex.getMessage().substring(0, DEFAULT_MESSAGE_LEN));
    }

}