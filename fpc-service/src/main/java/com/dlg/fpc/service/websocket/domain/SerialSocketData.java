package com.dlg.fpc.service.websocket.domain;

import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * WebSocket连接数据
 *
 */
@Data
@AllArgsConstructor
public class SerialSocketData  {
    private Long userId;
    private Session session;

}