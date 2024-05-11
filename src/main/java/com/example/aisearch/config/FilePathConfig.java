package com.example.aisearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 作者：韦作旭
 * 时间：2024-05-11 上午2:19
 * 描述：上传路径映射
 **/
@Configuration
public class FilePathConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") //虚拟url路径
                .addResourceLocations("file:C:/0_code/00.program/life/images/"); //真实本地路径
    }
}
