package com.ruoyi.framework.web.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: TODO
 * @author: wangming
 * @date: 2021年06月09日 23:05
 * 处理跨域问题
 */
@Configuration
public class Appconfig extends WebMvcConfigurerAdapter  {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true)
                //最大生存时间为3600秒
                .maxAge(3600);
    }
}
