package com.dlg.fpc.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 程序事件监听
 * 用于在某些阶段执行任务
 *
 * @author lingui
 * @Date 2022-06-02 14:17:16
 */
@Slf4j
@Component
public class AppEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent readyEvent) {
        try {

        } catch (Throwable e) {

        }
    }

}