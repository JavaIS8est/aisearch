package com.example.aisearch.entity;

import lombok.Data;

/**
 * 作者：韦作旭
 * 时间：2024-05-07 下午1:02
 * 描述：应知应会分页查询
 **/
@Data
public class YzyhVo {
    private Integer pageNum;//页号
    private Integer pageSize;//每页显示多少条数据
    //搜索内容
    private String firstTitle;//所属类型
    private String secondTitle;//问题
    private String contetn;//解答
}
