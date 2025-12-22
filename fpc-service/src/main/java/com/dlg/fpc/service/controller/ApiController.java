package com.dlg.fpc.service.controller;

import com.dlg.fpc.service.comm.anno.LogOperation;
import com.dlg.fpc.service.dto.Result;
import com.dlg.fpc.service.dto.req.MessageReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
public class ApiController {

    @GetMapping("ping")
    @LogOperation(value = "测试api")
    public Result<String> ping() {
        log.info("ping ");
        String message = "pong" + " " + System.currentTimeMillis();
        return new Result<String>().ok(message);
    }

    @PostMapping("msg")
    @LogOperation(value = "消息")
    public Result<String> msg(@Validated @RequestBody MessageReq message) {
        String msg = message.getMessage() + " " + System.currentTimeMillis();
        return new Result<String>().ok(msg);
    }

}
