package com.dlg.fpc.service.config;

import com.dlg.fpc.service.util.WxChatEmail;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 组件自动注入
 */
@Component
@Slf4j
public class CompDIConfig {

    @Resource
    YmlConfigVal configVal;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WxChatEmail emailUtil() {
        return new WxChatEmail(
                configVal.getEmailAccount(),
                configVal.getEmailPassword()
        );
    }

}
