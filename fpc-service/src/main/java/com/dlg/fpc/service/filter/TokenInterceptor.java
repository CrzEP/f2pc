package com.dlg.fpc.service.filter;

import com.dlg.fpc.service.util.HttpContextUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * token 拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 使用cookie-token检查用户
        String token = HttpContextUtils.getCookie(request, "token");
        if (StringUtils.isBlank(token)) {
            String uri = request.getRequestURI();
            log.error("uri: {} token is blank", uri);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
