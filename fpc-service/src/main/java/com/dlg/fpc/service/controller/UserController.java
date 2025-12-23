package com.dlg.fpc.service.controller;

import com.dlg.fpc.service.comm.anno.LogOperation;
import com.dlg.fpc.service.dto.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    @LogOperation(value = "登陆")
    public Result<String> login(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().getAttribute("token");
        return new Result<String>().ok("success");
    }

}
