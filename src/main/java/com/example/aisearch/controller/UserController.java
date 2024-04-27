package com.example.aisearch.controller;

import com.alibaba.fastjson.JSON;
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
        User user = userService.getOne(new QueryWrapper<User>().eq("login_name",userName));
        if (user.getLoginPassword().equals(password)){
            map.put("user",user);
            session.setAttribute("user",user);
            return  R.ok(map);
        }
        return R.error(400,"输入的账号或密码有误");
    }
    @PostMapping("/userset")
    @ResponseBody
    public R userSetting (HttpSession session){
        Map<String,Object> map =new HashMap<>();
        Object o = session.getAttribute("user");
        map.put("user",o);
        return R.ok(map);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public R userUpdate(@RequestBody JSONObject data,HttpSession session){
        User user =(User)session.getAttribute("user");
         user.setName(data.getString("username"));
         user.setTel(data.getString("phone"));
         user.setEMail(data.getString("email"));
         user.setCompany(data.getString("company"));
         user.setJob(data.getString("job"));
         userService.updateById(user);
        return R.ok("更新成功");
    }

    @PostMapping("updatePassword")
    @ResponseBody
    public R passwordUpdate(@RequestBody JSONObject data ,HttpSession session){
        User user =(User)session.getAttribute("user");
        Md5 m= new Md5();
        System.out.println(data.toString());
        String oldPassword =m.Md5solt(data.getString("oldPass"),user.getLoginName());
        if (null!=oldPassword&&!oldPassword.isEmpty()){
            if (user.getLoginPassword().equals(oldPassword)){
                String newPassword = m.Md5solt(data.getString("newPass"),user.getLoginName());
                user.setLoginPassword(newPassword);
                userService.updateById(user);
                session.removeAttribute("user");
                return R.ok("更新密码成功，请退出后重新登陆！");
            }else{
                return R.error(400,"旧密码输入错误请重试！");
            }
        }
        return R.error(404,"错误请求，联系管理员！");
    }

}
