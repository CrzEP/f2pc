package com.dlg.fpc.service.comp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MinSchedule {

    /**
     * 每2分钟执行一次任务
     */
    @Scheduled(fixedRate = 1000_60_2)
    public void execute() {
        log.info("MinSchedule execute");
    }

}
