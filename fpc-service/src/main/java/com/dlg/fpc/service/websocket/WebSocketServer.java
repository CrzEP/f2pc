package com.dlg.fpc.service.websocket;

import com.dlg.fpc.service.comm.TConst;
import com.dlg.fpc.service.util.JsonUtils;
import com.dlg.fpc.service.websocket.domain.SerialSocketData;
import com.dlg.fpc.service.websocket.domain.WsMessage;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lingui
 * @Date 2022-08-29 09:18:48
 */
@Slf4j
@Component
@ServerEndpoint(value = "/dlgWs", configurator = WebSocketConfig.class)
public class WebSocketServer {

    /**
     * 客户端连接信息
     */
    private final static Map<String, SerialSocketData> SERVERS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        Long userId = (Long) session.getUserProperties().get(TConst.WEBSOCKET_USER_KEY);
        log.info("有新的客户端连接了: {}", session.getId());
        SERVERS.put(session.getId(), new SerialSocketData(userId, session));

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端发来的消息: {}, {}", session.getId(), message);
    }

    @OnClose
    public void onClose(Session session) {
        //客户端断开连接
        removeSession(session.getId());
        log.info("用户断开了, id为:{}", session.getId());
    }

    private void removeSession(String id) {
        SERVERS.remove(id);
    }

    @OnError
    public void OnError(Session session, Throwable e) {
        removeSession(session.getId());
        log.error("会话：{} 异常: {}", session.getId(), e.getMessage());
    }

    /**
     * 发送信息给全部用户
     *
     * @param message 消息内容
     */
    public void sendMessageAll(WsMessage message) {
        SERVERS.values().forEach(info -> sendMessage(info.getSession(), message));
    }

    public void sendMessage(Session session, WsMessage message) {
        synchronized (session) {
            try {
                session.getBasicRemote().sendText(JsonUtils.toJson(message));
            } catch (IOException e) {
                log.error("websocket发送消息超时：",e);
            }
        }
    }
}