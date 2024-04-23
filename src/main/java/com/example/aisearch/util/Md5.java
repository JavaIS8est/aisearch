package com.example.aisearch.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;

/**
 * 作者：韦作旭
 * 时间：2024-04-23 下午3:15
 * 描述：md5工具类
 **/
public class Md5 {

    private static final String Solt ="qzgs";
    //md5加密
    public String Md5(String password){
        return  DigestUtils.md5Hex(password);
    }
    //md5带盐加密
    public String Md5s(String password){
        return  DigestUtils.md5Hex(password+Solt);
    }
    //用户名做盐
    public String Md5solt(String password,String Solt){
        return  DigestUtils.md5Hex(password+Solt);
    }
}
