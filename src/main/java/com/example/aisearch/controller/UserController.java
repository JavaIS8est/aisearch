package com.example.aisearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.aisearch.entity.R;
import com.example.aisearch.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者：韦作旭
 * 时间：2024-04-23 下午4:19
 * 描述：用户控制层
 **/
@RestController
@RequestMapping("/user")
public class UserController  {
    @Autowired
    private IuserService userService;

    @PostMapping("/login")
    @ResponseBody
    public R login(@RequestBody JSONObject data){
        System.out.println(data);
        //return R.error(400,"连接失败！");
        return  R.ok("成");
    }
}
