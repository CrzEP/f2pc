package com.dlg.fpc.service.controller;

import com.dlg.fpc.service.dto.Result;
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

    @RequestMapping("/error")
    public Result<String> error() {
        Result<String> result = new Result<>();
        result.setCode(500);
        result.setMsg("error");
        return result;
    }


}
