package com.example.aisearch.config;

import com.example.aisearch.LoginIntercept.LoginIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 作者：韦作旭
 * 时间：2024-04-24 22:11
 * 描述：
 **/
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercept loginIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercept).
                addPathPatterns("/**").
                excludePathPatterns("/login").
                excludePathPatterns("/user/login").
                excludePathPatterns(".html").
                excludePathPatterns("/jdsc/search").
                excludePathPatterns("/yzyh/search").
                excludePathPatterns(".html").
                excludePathPatterns("/**/*.css").
                   excludePathPatterns("/**/*.js").
                excludePathPatterns("/**/*.jpg").
                excludePathPatterns(".png").
                excludePathPatterns("/images/**");
    }
}