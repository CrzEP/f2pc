package com.dlg.fpc;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.dlg.fpc.comp.LsComp;
import com.dlg.fpc.ui.LongSitGui;
import com.dlg.fpc.util.DateUtils;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Slf4j
@SpringBootApplication
public class Application {

    @Getter
    private static LongSitGui longSitGui;

    public static void main(String[] args) {
        // 启动 Swing 应用程序
        longSitGui = new LongSitGui();
        SpringApplication.run(Application.class, args);
        log.info("========硬件服务已启动========");
        log.info("程序启动号：" + DateUtils.dateTimeNow());
    }

}