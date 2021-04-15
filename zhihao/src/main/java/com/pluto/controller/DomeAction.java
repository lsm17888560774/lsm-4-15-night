package com.pluto.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author Charon <1819248612@qq.com>
 * @create 2020-11-18-14:30
 * @Version 1.0.0
 */
@RestController
public class DomeAction {
    //添加了一个方法
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "no hello world";
    }
}
