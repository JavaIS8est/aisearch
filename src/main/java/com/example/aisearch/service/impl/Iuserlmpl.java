package com.example.aisearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aisearch.entity.User;
import com.example.aisearch.mapper.userMapper;
import com.example.aisearch.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者：韦作旭
 * 时间：2024-04-23 上午11:06
 * 描述：
 **/
@Service("userService")
public class Iuserlmpl extends ServiceImpl<userMapper, User> implements IuserService {

    @Autowired
    private userMapper usermapper;
}
