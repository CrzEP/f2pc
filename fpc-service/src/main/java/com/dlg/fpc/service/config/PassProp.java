package com.dlg.fpc.service.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@PropertySource(value = "classpath:config/secretKey.yml")
@Data
public class PassProp {

    private String key1;

}
