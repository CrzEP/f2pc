package com.dlg.fpc.service.comp;

import com.dlg.fpc.service.config.YmlConfigVal;
import com.dlg.fpc.service.util.JsonUtils;
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
    public WxChatResponse sendMessage(String content) {
        // 固定的消息发送者
        wxChatMessage.setText(new WxText(content));
        String url = configVal.getComWChatUrl();
        ResponseEntity<String> respond = restTemplate.postForEntity(
                url, wxChatMessage, String.class
        );
        String body = respond.getBody();
        WxChatResponse wxChatResponse = JsonUtils.toClass(body, WxChatResponse.class);
        if (!respond.getStatusCode().is2xxSuccessful()) {
            log.error("wxChatResponse error code : {}", wxChatResponse.getCode());
            log.error("wxChatResponse error message : {}", wxChatResponse.getErrmsg());
        }
        return wxChatResponse;
    }

    @Data
    public static class WxChatResponse {
        private int code;
        private String errmsg;

        public boolean ifSuccess() {
            return code == 0;
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
