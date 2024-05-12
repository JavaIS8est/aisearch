package com.example.aisearch.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aisearch.entity.JdscVo;
import com.example.aisearch.util.R;
import com.example.aisearch.entity.JiDianShouCe;
import com.example.aisearch.service.IjidianshouceService;
import com.mysql.cj.xdevapi.JsonArray;
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
    //微信小程序端口
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
                    .orderByDesc("search_times"));
                    listUpdate(jdscList);
                    map.put("message",jdscList);
        }else if (null != keyword &keyword.length()<=5) {
            List<JiDianShouCe> jdscList = jdscService.list(new QueryWrapper<JiDianShouCe>()
                    .like(StringUtils.isNotBlank(keyword), "first_title", keyword)
                    .or()
                    .like(StringUtils.isNotBlank(keyword), "second_title", keyword)
                    .orderByDesc("search_times"));
                    listUpdate(jdscList);
                    map.put("message", jdscList);
        }else {
            return R.error(400,"关键词有误重新查询,请联系管理员");
        }
        return R.ok(map);
    }
    //pc端口
    @GetMapping("/jdscpage")
    @ResponseBody
    public R jdscpage(Integer page,Integer limit,String ft,String st,String ct){
        System.out.println("ft:"+ft+"---st:"+st+"---ct:");
        System.out.println(page+"----"+limit);
        Map<String,Object> map =new HashMap<>();
        JdscVo jdscVo = new JdscVo();
        if (null!=ft&&!ft.isEmpty()){
            jdscVo.setFirstTitle(ft);
        }
        if (null!=st&&!st.isEmpty()){
            jdscVo.setSecondTitle(st);
        }
        if (null!=ct&&!ct.isEmpty()){
            jdscVo.setContetn(ct);
        }
        jdscVo.setPageNum(page);
        jdscVo.setPageSize(limit);
        IPage<JiDianShouCe> jdscpage =queryList(jdscVo);
        List<JiDianShouCe> jdsclist = jdscpage.getRecords();
        JSONArray array = new JSONArray();
        for (JiDianShouCe j :jdsclist){
            JSONObject js = (JSONObject)JSONObject.toJSON(j);
            array.add(js);
        }
        map.put("data",array);
        map.put("page",jdscpage);
        map.put("code",0);
        map.put("count",jdscpage.getTotal());
        return R.ok(map);
    }

    @PostMapping("jdscset")
    @ResponseBody
    public R jdscSetting (Integer id){
        //System.out.println(id);
        Map<String,Object> map =new HashMap<>();
        JiDianShouCe jiDianShouCe = jdscService.getById(id);
        map.put("data",jiDianShouCe);
        return R.ok(map);
    }
    @PostMapping("updateJdsc")
    @ResponseBody
    public R jdscUpdate(@RequestBody JSONObject data){
        JiDianShouCe jdsc = jdscService.getById(data.getString("id"));
        if (!data.getString("firstTitle").isEmpty()){
            jdsc.setFirstTitle(data.getString("firstTitle"));
        }
        if (!data.getString("secondTitle").isEmpty()){
            jdsc.setSecondTitle(data.getString("secondTitle"));
        }
        if (!data.getString("content").isEmpty()){
            jdsc.setContent(data.getString("content"));
        }
        if (!data.getString("imageName").isEmpty()){
        if (jdsc.getHaveImage()){
            jdsc.setImageName(data.getString("imageName"));
        }else
        {
            jdsc.setHaveImage(true);
            jdsc.setImageName(data.getString("imageName"));
        }}
        jdscService.updateById(jdsc);
        return R.ok();
    }

    @PostMapping("saveJdsc")
    @ResponseBody
    public R jdscSave(@RequestBody JSONObject data){
        JiDianShouCe jdsc = new JiDianShouCe();
        if (!data.getString("firstTitle").isEmpty()){
            jdsc.setFirstTitle(data.getString("firstTitle"));
        }
        if (!data.getString("secondTitle").isEmpty()){
            jdsc.setSecondTitle(data.getString("secondTitle"));
        }
        if (!data.getString("content").isEmpty()){
            jdsc.setContent(data.getString("content"));
        }
        if (!data.getString("imageName").isEmpty()){
                jdsc.setHaveImage(true);
                jdsc.setImageName(data.getString("imageName"));
            }
        jdsc.setLastSearch(new Date());
        jdscService.save(jdsc);
        return R.ok();
    }

    private void listUpdate(List<JiDianShouCe> list){
        for (JiDianShouCe jdsc : list){
            jdsc.setSearchTimes(jdsc.getSearchTimes()+1);
            jdsc.setLastSearch(new Date());
        }
        jdscService.updateBatchById(list);
    }

    private IPage<JiDianShouCe> queryList(JdscVo jdscVo){
        IPage<JiDianShouCe> page = new Page<>(jdscVo.getPageNum(),jdscVo.getPageSize());
        QueryWrapper<JiDianShouCe> queryWrapper =new QueryWrapper<>();
        if(ObjectUtils.isNotNull(jdscVo.getFirstTitle())){
            queryWrapper.like("first_title",jdscVo.getFirstTitle());
        }
        if(ObjectUtils.isNotNull(jdscVo.getSecondTitle())){
            queryWrapper.like("second_title",jdscVo.getSecondTitle());
        }
        if(ObjectUtils.isNotNull(jdscVo.getContetn())){
            queryWrapper.like("content",jdscVo.getContetn());
        }
        IPage<JiDianShouCe> pagelist = jdscService.page(page,queryWrapper);
        return pagelist;
    }
}


