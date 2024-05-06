package com.example.aisearch.entity;

import lombok.Data;

import java.util.List;

/**
 * 作者：韦作旭
 * 时间：2024-04-28 下午3:59
 * 描述：机电手册（分页查询）
 **/
@Data
public class JdscVo {
    private Integer pageNum;//页号
    private Integer pageSize;//每页显示多少条数据
    //搜索内容
    private String firstTitle;//所属系统
    private String secondTitle;//问题
    private String contetn;//解答

}
