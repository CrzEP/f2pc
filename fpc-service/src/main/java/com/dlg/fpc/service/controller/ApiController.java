package com.dlg.fpc.service.controller;

import com.dlg.fpc.service.comm.anno.LogOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("ping")
    @LogOperation(value = "测试api")
    public String ping() {
        log.info("ping ");
        return "pong" + " " +System.currentTimeMillis();
    }

    @GetMapping("msg/{msg}")
    @LogOperation(value = "消息测试api")
    public String msg(@PathVariable("msg") String msg) {
        return msg + " " +System.currentTimeMillis();
    }

}
