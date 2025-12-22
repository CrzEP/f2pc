package com.dlg.fpc.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模版前端控制器
 */
@Slf4j
@RestController
@RequestMapping("/web")
public class ThyWebController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
