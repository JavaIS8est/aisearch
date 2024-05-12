package com.example.aisearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aisearch.entity.JdscVo;
import com.example.aisearch.entity.JiDianShouCe;
import com.example.aisearch.entity.YzyhVo;
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
                    .orderByDesc("search_times"));
            listUpdate(yzyhlist);
            map.put("message",yzyhlist);
        }else if (null != keyword &keyword.length()<=5) {
            List<YingZhiYingHui> yzyhlist = yzyhService.list(new QueryWrapper<YingZhiYingHui>()
                    .like(StringUtils.isNotBlank(keyword), "first_title", keyword)
                    .or()
                    .like(StringUtils.isNotBlank(keyword), "second_title", keyword)
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

    //pc端口
    @GetMapping("/yzyhpage")
    @ResponseBody
    public R jdscpage(Integer page,Integer limit,String ft,String st,String ct){

        //System.out.println(page+"----"+limit);
        Map<String,Object> map =new HashMap<>();
        YzyhVo yzyhVo = new YzyhVo();
        if (null!=ft&&!ft.isEmpty()){
            yzyhVo.setFirstTitle(ft);
        }
        if (null!=st&&!st.isEmpty()){
            yzyhVo.setSecondTitle(st);
        }
        if (null!=ct&&!ct.isEmpty()){
            yzyhVo.setContetn(ct);
        }
        yzyhVo.setPageNum(page);
        yzyhVo.setPageSize(limit);
        IPage<YingZhiYingHui> yzyhpage =queryList(yzyhVo);
        List<YingZhiYingHui> yzyhlist = yzyhpage.getRecords();
        JSONArray array = new JSONArray();
        for (YingZhiYingHui j :yzyhlist){
            JSONObject js = (JSONObject)JSONObject.toJSON(j);
            array.add(js);
        }
        map.put("data",array);
        map.put("page",yzyhpage);
        map.put("code",0);
        map.put("count",yzyhpage.getTotal());
        return R.ok(map);
    }

    @PostMapping("yzyhset")
    @ResponseBody
    public R yzyhSetting (Integer id){
        //System.out.println(id);
        Map<String,Object> map =new HashMap<>();
        YingZhiYingHui yingZhiYingHui = yzyhService.getById(id);
        map.put("data",yingZhiYingHui);
        return R.ok(map);
    }
    @PostMapping("updateYzyh")
    @ResponseBody
    public R jdscUpdate(@RequestBody JSONObject data){
        YingZhiYingHui yzyh = yzyhService.getById(data.getString("id"));
        if (!data.getString("firstTitle").isEmpty()){
            yzyh.setFirstTitle(data.getString("firstTitle"));
        }
        if (!data.getString("secondTitle").isEmpty()){
            yzyh.setSecondTitle(data.getString("secondTitle"));
        }
        if (!data.getString("content").isEmpty()){
            yzyh.setContent(data.getString("content"));
        }
        if (!data.getString("imageName").isEmpty()){
            if (yzyh.getHaveImage()){
                yzyh.setImageName(data.getString("imageName"));
            }else
            {
                yzyh.setHaveImage(true);
                yzyh.setImageName(data.getString("imageName"));
            }}
        yzyhService.updateById(yzyh);
        return R.ok();
    }

    @PostMapping("saveYzyh")
    @ResponseBody
    public R yzyhSave(@RequestBody JSONObject data){
        YingZhiYingHui yzyh = new YingZhiYingHui();
        if (!data.getString("firstTitle").isEmpty()){
            yzyh.setFirstTitle(data.getString("firstTitle"));
        }
        if (!data.getString("secondTitle").isEmpty()){
            yzyh.setSecondTitle(data.getString("secondTitle"));
        }
        if (!data.getString("content").isEmpty()){
            yzyh.setContent(data.getString("content"));
        }
        if (!data.getString("imageName").isEmpty()){
            yzyh.setHaveImage(true);
            yzyh.setImageName(data.getString("imageName"));
        }
        yzyh.setLastSearch(new Date());
        yzyhService.save(yzyh);
        return R.ok();
    }
    private IPage<YingZhiYingHui> queryList(YzyhVo yzyhVo){
        IPage<YingZhiYingHui> page = new Page<>(yzyhVo.getPageNum(),yzyhVo.getPageSize());
        QueryWrapper<YingZhiYingHui> queryWrapper =new QueryWrapper<>();
        if(ObjectUtils.isNotNull(yzyhVo.getFirstTitle())){
            queryWrapper.like("first_title",yzyhVo.getFirstTitle());
        }
        if(ObjectUtils.isNotNull(yzyhVo.getSecondTitle())){
            queryWrapper.like("second_title",yzyhVo.getSecondTitle());
        }
        if(ObjectUtils.isNotNull(yzyhVo.getContetn())){
            queryWrapper.like("content",yzyhVo.getContetn());
        }
        IPage<YingZhiYingHui> pagelist =yzyhService.page(page,queryWrapper);
        return pagelist;
    }
}
