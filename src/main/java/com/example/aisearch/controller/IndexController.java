package com.example.aisearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者：韦作旭
 * 时间：2024-04-09-16:33
 * 描述：接入前端页面
 **/
@RequestMapping
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){ return "login";}

}
