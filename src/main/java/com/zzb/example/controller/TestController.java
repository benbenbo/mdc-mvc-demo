package com.zzb.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XBird
 * @date 2021/12/28
 **/
@Slf4j
@RestController
public class TestController {
    @PostMapping(value="/test")
    public String test(){
        log.info("访问test接口");
        return "test";
    }

    @GetMapping(value="/hello")
    public String hello(){
        log.info("访问hello接口");
        return "hello";
    }
}
