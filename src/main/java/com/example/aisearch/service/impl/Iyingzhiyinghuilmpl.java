package com.example.aisearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aisearch.entity.YingZhiYingHui;
import com.example.aisearch.mapper.yingzhiyinghuiMapper;
import com.example.aisearch.service.IyingzhiyinghuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("yingzhiyinghuiService")
public class Iyingzhiyinghuilmpl extends ServiceImpl<yingzhiyinghuiMapper, YingZhiYingHui> implements IyingzhiyinghuiService {
    @Autowired
    private yingzhiyinghuiMapper yzyhMapper;
}
