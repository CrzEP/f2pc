package com.dlg.fpc.service.comp;

import com.dlg.fpc.service.util.WxChatEmail;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 通知组件
 */
@Slf4j
@Component
public class NoticeComp {

    @Resource
    WxChatMessageComp  wxChatMessageComp;
    @Resource
    WxChatEmail wxChatEmail;

    public void notice(String message) {
        log.info("MinSchedule executePerMin");
        wxChatMessageComp.sendMessage("这是一条测试消息");
        wxChatEmail.sendMail(
                "FPC服务",
                "duanlingui@xxcenter.cn",
                "ttest title测试标题",
                "这是一段邮件正文abc"
        );
    }

}
