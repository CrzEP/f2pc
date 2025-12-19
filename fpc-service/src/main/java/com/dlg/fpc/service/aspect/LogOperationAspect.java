package com.dlg.fpc.service.aspect;


import com.dlg.fpc.service.comm.anno.LogOperation;
import com.dlg.fpc.service.entity.ApiLogEntity;
import com.dlg.fpc.service.service.ApiLogService;
import com.dlg.fpc.service.util.HttpContextUtils;
import com.dlg.fpc.service.util.IpUtils;
import com.dlg.fpc.service.util.JsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志，切面处理类
 *
 * @author lingui
 */
@Aspect
@Component
@Slf4j
public class LogOperationAspect {

    @Resource
    ApiLogService logService;

    @Pointcut("@annotation(com.dlg.fpc.service.comm.anno.LogOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //执行方法
            Object result = point.proceed();
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, true);
            return result;
        } catch (Exception e) {
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, false);
            throw e;
        }
    }

    /**
     * 保存日志
     *
     * @param joinPoint 切入点
     * @param time      耗时
     * @param status    请求状态
     * @throws Exception 异常
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time, boolean status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);
        // 若标记不记录日志则结束
        if (annotation == null) {
            return;
        }
        ApiLogEntity entity = new ApiLogEntity();
        Date now = new Date();
        entity.setRequestTime(now);
        entity.setCreateTime(now);
        //注解上的描述
        entity.setApi(annotation.value());
        // 耗时
        entity.setCost(time);
        // 状态
        entity.setStatus(status);

        //请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (null != request) {
            entity.setIpAddr(IpUtils.getIpAddr(request));
            // 默认只记录一个
            String header = request.getHeader(HttpHeaders.USER_AGENT);
            entity.setHeader(header);
            entity.setUrl(request.getRequestURI());
            entity.setMethod(request.getMethod());
        }
        //请求参数
        Object[] args = joinPoint.getArgs();
        try {
            if (null != args && args.length>0) {
                String params = JsonUtils.toJson(args[0]);
                entity.setRequestParam(params);
            }
        } catch (Exception e) {
            log.error("日志异常：",e);
        }
        //保存到DB
        logService.save(entity);
    }
}