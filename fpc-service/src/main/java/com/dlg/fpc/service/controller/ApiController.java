package com.dlg.fpc.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("msg/{msg}")
    public String msg(@PathVariable("msg") String msg) {
        return msg + " " +System.currentTimeMillis();
    }

}
