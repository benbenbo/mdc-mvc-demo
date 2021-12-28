package com.zzb.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XBird
 * @date 2021/12/28
 **/
@RestController
public class TestController {
    @PostMapping(value="/test")
    public String test(){
        return "test";
    }

    @GetMapping(value="/hello")
    public String hello(){
        return "/hello";
    }
}
