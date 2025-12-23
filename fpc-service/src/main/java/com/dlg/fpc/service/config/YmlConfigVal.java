package com.dlg.fpc.service.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * yml 配置值
 *
 * @author lingui
 */
@Component
@Getter
@Slf4j
public class YmlConfigVal {

    @Value("${account.sm2key.publick-key}")
    private String publicKey;
    @Value("${account.sm2key.private-key}")
    private String privateKey;
    @Value("${account.email.enable}")
    Boolean emailEnable;
    @Value("${account.email.account}")
    String emailAccount;
    @Value("${account.email.password}")
    String emailPassword;
    @Value("${account.comWChat.url}")
    String comWChatUrl;

    @PostConstruct
    void init() {
        log.info("邮件是否可用：{}", emailEnable);
        log.info("邮件账户：{}", emailAccount);
    }

}