package com.dlg.fpc.service.comm.anno;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author lingui
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {

	String value() default "";

}