package com.example.aisearch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("yingzhiyinghui")
public class YingZhiYingHui {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //唯一标识ID
    private String firstTitle;
    //查询内容主题干
    private String secondTitle;
    //查询内容副题干
    private String content;
    //查询内容解答
    private Integer searchTimes;
    //该内容被引用的次数
    private Boolean haveImage;
    //回答是否带图片
    private String imageName;
    //图片名称
    private Date lastSearch;
    //上次查询时间
}
