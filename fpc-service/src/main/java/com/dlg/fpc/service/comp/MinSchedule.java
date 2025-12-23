package com.dlg.fpc.service.comp;

import com.dlg.fpc.service.util.WxChatEmail;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MinSchedule {

    @Resource
    WxChatMessageComp  wxChatMessageComp;
    @Resource
    WxChatEmail wxChatEmail;

    /**
     * 每2分钟执行一次任务
     */
    @Scheduled(fixedRate = 1000_60_2)
    public void executePerMin() {
        log.info("MinSchedule executePerMin");
        wxChatMessageComp.sendMessage("这是一条测试消息");
        wxChatEmail.sendMail(
                "FPC服务",
                "duanlingui@xxcenter.cn",
                "ttest title测试标题",
                "这是一段邮件正文abc"
        );
    }

    /**
     * 每1小时执行一次任务
     */
    @Scheduled(fixedRate = 1000_60_60)
    public void executePerHour() {
        log.info("MinSchedule executePerHour");
    }


}
