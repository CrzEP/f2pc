package com.dlg.fpc.service.controller.websocket.domain;

import lombok.Data;

/**
 * @author lingui
 * @Date 2022-08-17 15:58:24
 */
@Data
public class WsMessage{

    private int type;

    private String message;

    private Object data;

    private long time;

    public WsMessage(int type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }
}