package com.example.aisearch.LoginIntercept;

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
//                excludePathPatterns("/**/login.html").
//                excludePathPatterns("/api/**").
//                excludePathPatterns("/css/**").
//                excludePathPatterns("/images/**").
//                excludePathPatterns("/js/**").
//                excludePathPatterns("/lib/**").
//                excludePathPatterns("/static/**").
                excludePathPatterns("/**/*.css").
                   excludePathPatterns("/**/*.js").
                excludePathPatterns("/**/*.jpg");
    }
}