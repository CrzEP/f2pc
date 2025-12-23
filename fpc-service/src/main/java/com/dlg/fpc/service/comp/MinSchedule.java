package com.dlg.fpc.service.comp;

import com.dlg.fpc.service.util.WxChatEmail;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MinSchedule {



    /**
     * 每1小时执行一次任务
     */
    @Scheduled(fixedRate = 1000_60_60)
    public void executePerHour() {
        log.info("MinSchedule executePerHour");
    }


}
