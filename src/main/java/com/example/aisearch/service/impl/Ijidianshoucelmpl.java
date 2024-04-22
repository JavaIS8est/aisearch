package com.example.aisearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aisearch.entity.JiDianShouCe;
import com.example.aisearch.mapper.jidianshouceMapper;
import com.example.aisearch.service.IjidianshouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jidianshouceService")
public class Ijidianshoucelmpl extends ServiceImpl<jidianshouceMapper, JiDianShouCe> implements IjidianshouceService {

        @Autowired
        private  jidianshouceMapper jdscMapper;
}
