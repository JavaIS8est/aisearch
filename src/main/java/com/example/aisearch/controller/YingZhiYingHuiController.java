package com.example.aisearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.aisearch.util.R;
import com.example.aisearch.entity.YingZhiYingHui;
import com.example.aisearch.service.IyingzhiyinghuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yzyh")
public class YingZhiYingHuiController {
    @Autowired
    private IyingzhiyinghuiService yzyhService;

    @PostMapping("/search")
    @ResponseBody
    public R search(@RequestBody JSONObject data){
        String keyword =data.getString("keyword");
        Map<String,Object> map =new HashMap<>();
        if (null != keyword &keyword.length()>5) {
            List<YingZhiYingHui> yzyhlist = yzyhService.list(new QueryWrapper<YingZhiYingHui>()
                    .like(StringUtils.isNotBlank(keyword), "second_title", keyword)
                    .or()
                    .like(StringUtils.isNotBlank(keyword), "content", keyword)
                    .last("limit 5")
                    .orderByDesc("search_times"));
            listUpdate(yzyhlist);
            map.put("message",yzyhlist);
        }else if (null != keyword &keyword.length()<=5) {
            List<YingZhiYingHui> yzyhlist = yzyhService.list(new QueryWrapper<YingZhiYingHui>()
                    .like(StringUtils.isNotBlank(keyword), "second_title", keyword)
                    .last("limit 5")
                    .orderByDesc("search_times"));
            listUpdate(yzyhlist);
            map.put("message", yzyhlist);
        }else {
            return R.error(400,"关键词有误重新查询,请联系管理员");
        }
        return R.ok(map);
    }
    private void listUpdate(List<YingZhiYingHui> list){
        for (YingZhiYingHui yzyh : list){
            yzyh.setSearchTimes(yzyh.getSearchTimes()+1);
            yzyh.setLastSearch(new Date());
        }
        yzyhService.updateBatchById(list);
    }
}
