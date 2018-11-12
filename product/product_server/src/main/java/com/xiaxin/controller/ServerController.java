package com.xiaxin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaxin on 2018/10/31.
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "product msg";
    }
}
