package com.dlg.fpc.service.comp;

import com.dlg.fpc.service.util.WxChatEmail;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * 通知组件
 */
@Slf4j
@Component
public class NoticeComp {

    @Resource
    WxChatMessageComp wxChatMessageComp;
    @Resource
    WxChatEmail wxChatEmail;

    private LinkedHashMap<String, String> cacheMap = new LinkedHashMap<>();

    /**
     * 通知
     *
     * @param message 消息
     */
    public void notice(String message) {
        log.info("notice");
        WxChatMessageComp.WxChatResponse wxChatResponse =
                wxChatMessageComp.sendMessage(message);
        boolean mailResult = wxChatEmail.sendMail(
                "FPC服务",
                "duanlingui@xxcenter.cn",
                "FPC服务",
                message
        );

    }

}
