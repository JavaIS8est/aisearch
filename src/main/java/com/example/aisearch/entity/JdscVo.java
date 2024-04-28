package com.example.aisearch.entity;

import java.util.List;

/**
 * 作者：韦作旭
 * 时间：2024-04-28 下午3:59
 * 描述：机电手册（分页查询）
 **/
public class JdscVo {
    private Integer current;//页号
    private Integer size;//每页显示多少条数据
    private Long total;//总数
    private List<JiDianShouCe> jdscList;//查询结果合集
}
