package com.example.aisearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.aisearch.util.R;
import com.example.aisearch.entity.JiDianShouCe;
import com.example.aisearch.service.IjidianshouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdsc")
public class JidianshouceController {
    @Autowired
    private IjidianshouceService jdscService;

    @PostMapping("/search")
    @ResponseBody
    public R search(@RequestBody JSONObject data){
        String keyword =data.getString("keyword");
        Map<String,Object> map =new HashMap<>();
        if (null != keyword &keyword.length()>5) {
            List<JiDianShouCe> jdscList = jdscService.list(new QueryWrapper<JiDianShouCe>()
                    .like(StringUtils.isNotBlank(keyword), "second_title", keyword)
                    .or()
                    .like(StringUtils.isNotBlank(keyword), "content", keyword)
                    .last("limit 5")
                    .orderByDesc("search_times"));
                    listUpdate(jdscList);
                    map.put("message",jdscList);
        }else if (null != keyword &keyword.length()<=5) {
            List<JiDianShouCe> jdscList = jdscService.list(new QueryWrapper<JiDianShouCe>()
                    .like(StringUtils.isNotBlank(keyword), "second_title", keyword)
                    .last("limit 5")
                    .orderByDesc("search_times"));
                    listUpdate(jdscList);
                    map.put("message", jdscList);
        }else {
            return R.error(400,"关键词有误重新查询,请联系管理员");
        }
        return R.ok(map);
    }
    private void listUpdate(List<JiDianShouCe> list){
        for (JiDianShouCe jdsc : list){
            jdsc.setSearchTimes(jdsc.getSearchTimes()+1);
            jdsc.setLastSearch(new Date());
        }
        jdscService.updateBatchById(list);
    }
}


