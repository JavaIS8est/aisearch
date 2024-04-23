package com.example.aisearch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 作者：韦作旭
 * 时间：2024-04-23 上午10:46
 * 描述：管理门户网站用户类
 **/
@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    //用户姓名

    private String loginName;
    //用户登陆账号

    private String loginPassword;
    //用户登陆密码

    private String company;
    //用户单位

    private String job;
    //用户岗位

    private String tel;
    //用户电话号码

    private String eMail;
    //用户邮箱

    private Date createTime;
    //创建时间
}
