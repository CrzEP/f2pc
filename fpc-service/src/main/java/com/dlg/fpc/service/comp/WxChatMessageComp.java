package com.dlg.fpc.service.comp;

import com.dlg.fpc.service.config.YmlConfigVal;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 微信消息组件
 */
@Component
@Slf4j
public class WxChatMessageComp {

    @Resource
    RestTemplate restTemplate;
    @Resource
    YmlConfigVal configVal;

    private final WxChatMessage wxChatMessage = new WxChatMessage();

    /**
     * 发送消息
     *
     * @param content 内容
     */
    public void sendMessage(String content) {
        // 固定的消息发送者
        wxChatMessage.setText(new WxText(content));
        String url = configVal.getComWChatUrl();
        ResponseEntity<String> respond = restTemplate.postForEntity(
                url, wxChatMessage, String.class
        );
        if (respond.getStatusCode().is2xxSuccessful()) {
            log.info("response: {}", respond.getBody());
        } else {
            log.error("response: {}", respond.getBody());
        }
    }

    @Data
    public static class WxChatMessage {

        private String msgtype = "text";

        private WxText text;

    }

    @Data
    @AllArgsConstructor
    public static class WxText {
        private String content;
    }

}
