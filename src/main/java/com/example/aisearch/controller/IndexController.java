package com.example.aisearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    @GetMapping("/user-setting")
    public String us(){return "user-setting";}
    @GetMapping("/user-password")
    public String up(){return "user-password";}
    @GetMapping("/login-out")
    public String loginOut(HttpSession session){session.removeAttribute("user"); return "login"; }
    @GetMapping("/table")
    public String table(){return "table";}
    @GetMapping("/table/edit")
    public String tableEdit(){return "edit";}
    @GetMapping("/table/add")
    public String tableAdd(){return "add";}
    @GetMapping("/table_y")
    public String table_y(){return "table_y";}
    @GetMapping("/table_y/edit_y")
    public String tableEdit_y(){return "edit_y";}
    @GetMapping("/table_y/add_y")
    public String tableAdd_y(){return "add_y";}
    @GetMapping("/welcome")
    public String welcome(){return "welcome-2";}
    @GetMapping("/welcome_1")
    public String welcome_1(){return "welcome-1";}


}
