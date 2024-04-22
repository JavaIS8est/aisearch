package com.example.aisearch.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 自定义返回json数据格式中日期格式化处理
 */
//public class CustomDateTimeSerializer extends JsonSerialize<Date> {
//
//    @Override
//    public void serializer(Date value, JsonGenerator gen , SerializerProvider serializers) throws IOException{
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        gen.writeString(sdf.format(value));
//    }
//}
