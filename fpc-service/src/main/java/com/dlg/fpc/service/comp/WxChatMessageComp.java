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
        // 获取url
        String url = configVal.getComWChatUrl();
        // 发送请求
        ResponseEntity<String> respond = restTemplate.postForEntity(
                url, wxChatMessage, String.class
        );
        // 获取响应
        String body = respond.getBody();
        WxChatResponse wxChatResponse = JsonUtils.toClass(body, WxChatResponse.class);
        // 网络传输层导致的请求失败
        if (!respond.getStatusCode().is2xxSuccessful()) {
            log.error("wxChatResponse error code : {}", respond.getStatusCode());
            log.error("wxChatResponse error body : {}", respond.getBody());
        }
        return wxChatResponse;
    }

    @Data
    public static class WxChatResponse {
        private int code;
        private String errmsg;

        /**
         * true 成功
         * @return true
         */
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
