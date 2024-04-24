package com.example.aisearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.aisearch.util.R;
import com.example.aisearch.entity.User;
import com.example.aisearch.service.IuserService;
import com.example.aisearch.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public R login(@RequestBody JSONObject data , HttpSession session){
        Md5 m= new Md5();
        Map<String,Object> map =new HashMap<>();
        String userName =data.getString("userName");
        String password =m.Md5solt(data.getString("password"),userName) ;
      System.out.println(userName+"-----"+password);
        User user = userService.getOne(new QueryWrapper<User>().eq("login_name",userName));
        if (user.getLoginPassword().equals(password)){
            map.put("user",user);
            session.setAttribute("user",user);
            return  R.ok(map);
        }
        return R.error(400,"输入的账号或密码有误");
    }


}
