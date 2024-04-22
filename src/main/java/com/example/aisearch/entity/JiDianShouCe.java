package com.example.aisearch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@TableName("jidianshouce")
public class JiDianShouCe {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String firstTitle;

    private String secondTitle;

    private String content;

    private Integer searchTimes;

    private Boolean haveImage;

    private String imageName;

//    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private Date lastSearch;
}
